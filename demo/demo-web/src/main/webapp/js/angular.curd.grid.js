
var assign = function(obj, prop, value) {
    if (typeof prop === "string"){
        prop = prop.split(".");
    }

    if (prop.length > 1) {
        var e = prop.shift();
        assign(obj[e] = Object.prototype.toString.call(obj[e]) === "[object Object]" ? obj[e] : {}, prop, value);
    }else{
        obj[prop[0]] = value;
    }
}

var fetch = function(obj, prop) {
    if (typeof prop === "string"){
        prop = prop.split(".");
    }

    if (prop.length > 1) {
        var e = prop.shift();
        return fetch(obj[e] = Object.prototype.toString.call(obj[e]) === "[object Object]" ? obj[e] : {},  prop);
    }else{
    	return obj[prop[0]];
    }
}

var Util = {}
Util.assign = assign;
Util.fetch = fetch;

var initService = function(app, config){
	
    app.factory('BusinessService', [function(){
		
	    var list = function (filter, postData, successCallback, errorCallback) {
	    	var localFilter = config.filterHandler(filter);
	    	var response = app.remote.page(localFilter, postData.pageNo, postData.pageSize);
	    	successCallback(response);
	    };
	    
	    var submit = function(item, successCallback, errorCallback) {
	    	var response;
	    	var add = false;
	    	if(item.__add){
	    		add = true;
	    	}
	    	delete item.__add;
	    	try{
	    		if(add){
	    	        app.remote.add(item);
	            }else{
	        	    app.remote.update(item);
	            }
	            successCallback();
	    	}catch(e){
	    		item.__add = add;
	    		errorCallback(e);
	    	}
	    	
	    };
	    
	    var del = function(item, successCallback, errorCallback){
	    	
		    confirm_call(config.deleteConfirmMsg, function(){
				try{
					var response = app.remote["delete"](item[config.key]);
					successCallback(response);
				}catch(e){
				    errorCallback(e);
				};
			 }, function(){
				  
			 });
		    
	     };
	 
	     var obj = {
	        list: function (filter, data, successCallback, errorCallback) {
	            return list(filter, data, successCallback, errorCallback);
	        },
	        submit: function (item, successCallback, errorCallback) {
	            return submit(item, successCallback, errorCallback);
	        },
	        del: function (item, successCallback, errorCallback) {
	            return del(item, successCallback, errorCallback);
	        }
	     }
	     
         for(var funName in config.functions){
        	obj[funName] = config.functions[funName];
         }
	     
	     return obj;
	}]);
}

var initController = function(app, config){
	
	app.controller(config.controller, ['$scope', '$sce', 'BusinessService', function($scope, $sce, BusinessService) {
		 
	       $scope.query = function () {
	        	 
	           var postData = {
	                pageNo: $scope.paginationConf.currentPage,
	                pageSize: $scope.paginationConf.itemsPerPage
	           }
	           var filter = angular.copy($scope.filter);
	           BusinessService.list(filter, postData, function(response){
	                $scope.data = response.data;
	                $scope.totalItems = response.count;
	           }, function(){
	           	    //handle error
	           });
	 
	      };
			 	     
		  $scope.del = function(obj){
		    	
		      BusinessService.del(obj, function(){
		    	  
		    	  $scope.$apply(function(){
		    		  $scope.query();
			      });
		    	  
		      }, function(){
		    	  
		      });
		    	
		  };
		  
		  $scope.trustSrcurl = function(data) {
		      return $sce.trustAsResourceUrl(data);
		  }
		   
		  $scope.edit = function(row){
			  $scope.selectRow = row;
		      $scope.currentItem = angular.copy(row);
		      $scope.currentItem.__add = false;
		      $('#addModal').modal(); 
		      config.beforeEdit(row, function(obj){
		    	  $scope.$apply(function(){
			    	  row = angular.copy(obj);
			    	  $scope.currentItem = angular.copy(obj);
			    	  $scope.currentItem.__add = false;
			      });
		      });
	      };
	      
	      $scope.add = function(){
		    	
		      $scope.currentItem = {};
		      $scope.currentItem.__add = true;
		      $('#addModal').modal(); 
		      config.beforeAdd($scope, function(obj){
		         
		      });
		  };
	    
		  $scope.updateLocalData = function(){
			  var find = false;
			  for(var i = $scope.data.length; i--;){
				  var d = $scope.data[i];
				  if($scope.currentItem[config.key] && d[config.key] == $scope.currentItem[config.key]){
					  $scope.data[i] = angular.copy($scope.currentItem);
					  find = true;
				  }
			  }
			  if(find==false){
				  $scope.data.unshift($scope.currentItem);
			  }
		  }
		  
	      $scope.submit = function(){
	    	
	    	  BusinessService.submit($scope.currentItem, function(){
	    		 $('#addModal').modal("hide"); 
	    		 if(config.refreshAfterSubmit){
	    		     $scope.query();
	    		 }else{
	    			 $scope.updateLocalData();
	    		 }
	    	  }, function(e){
	    	  	 alert(e);
	    	  });
	    	
	      };
	      
          for(var funName in config.functions){

        	  var fun = function(funName){
        		  $scope[funName] = BusinessService[funName];
        	  };
        	  
        	  fun(funName);
          }
          
          for(var i = config.selects.length; i--;){
          	  var select = config.selects[i];
	          var fun = function(select){
	        	  var name = select.name;
	      		  $scope[name] = select;
	      		  if(select.bind){
	      			  var val = name + '.selected.value';
	      		      $scope.$watch(val, function(){
	      		    	  Util.assign($scope, select.bind, $scope[name].selected.value);
	      		      });
	      		      
	      		      $scope.$watch(select.bind, function(){
	      		    	  $scope[name].selected.value = Util.fetch($scope, select.bind);
	      		      });
	      		  } 
	      	  };
	      	  
	      	  fun(select);
          }
   
          app.refresh = function(callback){
              $scope.$apply(callback);
          }
          
          app.scope = function(){
        	  return $scope;
          }
          
	      $scope.paginationConf = {
		      currentPage: 1,
		      itemsPerPage: config.pageSize
		  };
	      $scope.currentItem = {};
	      $scope.selectRow = null;
	      if(!$scope.filter){
	         $scope.filter = {};
	      }

	      $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', $scope.query);
	       
	      if(config.autoQuery == true){
	          $scope.$watch('filter', $scope.query, true);
	      }
	 }]);
}

var createTable = function(config){

    var __config = {
       app: 'myApp',  // app name
       controller: 'myController', // controller name
       autoQuery: true, // when query form change, whether to auto reflush ui
       path: '', // the service path to get data
       pageSize: 10, // how mush itme show for one page 
       key: 'id',
       functions: {  
       },
       filters: [ // define angular filter 	   
       ],
       selects:[ 
       ],
       beforeEdit: function(row, callback){
    	   
       },
       beforeAdd: function(score, callback){
    	   
       },
       filterHandler: function(filter){
    	   return filter;
       },
       deleteConfirmMsg: '是否确定删除当前行?该操作不可恢复!', //the delete msg
       refreshAfterSubmit: true, //if true, after submit, will query updated data from server, otherwise only update local data
    };

    if(config.hasOwnProperty('path') == false){
       throw new Error('path 属性未设置');
    }

    var methods = [ 'page', 'update', 'delete', 'add'];
    
    var attrs = ['path', 'app', 'control', 'autoQuery', 'pageSize', 'key', 'functions', 'filters', 'selects', 'beforeEdit', 'beforeAdd', 'deleteConfirmMsg',
                 'refreshAfterSubmit', 'filterHandler'];
    
    for(var i=0; i<attrs.length;i++){
    	var attr = attrs[i];
        if(config.hasOwnProperty(attr)){
        	__config[attr] = config[attr];
        }
    }
    
    for(var funName in __config.functions){
    	methods.push(funName);
    }
        
	var remote = new rpc.ServiceProxy("/services/" + __config.path, {
		asynchronous : false,
		methods : methods
	});
	
	var app = angular.module(__config.app, ['ui.bootstrap']);
    app.remote = remote;
    
    //filter define
    for(var i = __config.filters.length; i--;){
    	var filter = __config.filters[i];
    	  
        (function(filter){
    		
        	app.filter(filter.name, function($sce){
        		return function(k){
        			var value;
        	    	if(filter.data.hasOwnProperty(k)){
        	    		value = filter.data[k];
        	    	}else if(filter.data.hasOwnProperty('default')){
        	    		value = filter.data['default'];
        	    	}else{
        	    		value = "unknow filter value";
        	    	}
        	    	
        	    	if(filter.trustHtml){
    	    			return $sce.trustAsHtml(value);
    	    		}else{
    	    			return value;
    	    		}
        	    }
        	});
        	
    	})(filter);
    	  
    }
    
    app.filter('size', function($sce){
		return function(s){
			var mb = 1024 * 1024;
			var kb = 1024;
			if(s >= mb){
				var m = s / mb;
				return m.toFixed(2) + "MB";
			}else if(s >= kb){
				var k = s / kb;
				return k.toFixed(2) + "KB";
			}
			return s + "byte";
	    }
	});

    for(var i = __config.selects.length; i--;){
    	var select = config.selects[i];
        var fun = function(select){
      	    var name = select.name;
    		if(select.filter){
	          	var options = select.options;
		        app.filter(name, function($sce){
	        		return function(k){
	        			for(var j = options.length; j--;){
	        				var option = options[j];
	        				if(option.value==k){
	        					return option.label;
	        				}
	        			}
	        			return 'unknow filter value';
	        	    }
		        });
				
    		}
    		  
    	};
    	  
    	fun(select);
    }
    
     //method, params, confirm, reload
    app.call = function(){
  	   var length = arguments.length;
  	   if(length==0){
  		   throw new Error("call method not supply");
  	   }
  	   var method = arguments[0];
  	   var params = [];
  	   var confirm = null;
  	   var reload = false;
  	   var onSuccess = null;
  	   if(length>=2){
  		  params = arguments[1];
  	   }

  	   if(length>=3){
	  	   if(arguments[2] == null || typeof(arguments[2]) == 'string'){
	  		  confirm = arguments[2]; 
	 	   }else{
	 		  throw new Error('param error, param[confirm] must be string');
	 	   }
	   }
  	   
  	   if(length>=4){
	  	   if(typeof(arguments[3]) == 'boolean'){
	  		  reload = arguments[3]; 
	 	   }else{
	 		  throw new Error('param error, param[reload] must be boolean');
	 	   }
  	   }
  	   
  	   if(length>=5){
	  	   if(typeof(arguments[4]) == 'function'){
	  		 onSuccess = arguments[4]; 
	 	   }else{
	 		  throw new Error('param error, param[onSuccess] must be a function');
	 	   }
	   }
  	   
  	   var callRemote = function(){
  		   
  		   try{
			  var ret = app.remote[method].apply(null, params);
			  if(reload){
			     window.location.reload();  
			  }
			  if(onSuccess){
				  onSuccess(ret);
			  }
		   }catch(e){
			  alert(e);
		   };
  	   }

  	   if(confirm){
	  	   confirm_call(confirm, function(){
	  		   callRemote();
		   }, function(){
			  
		   });
  	   }else{
  		 callRemote();
  	   }
    }

	initController(app, __config);
	
	initService(app, __config);
	
	return {
		app: app,
	}
};


