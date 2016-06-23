

var Grid = function(tableId, config, gridConfig){
 
    this._tableId = tableId;
    this.init();
    this._setConfig(config);
    this._setGridConfig(gridConfig);
}

Grid.prototype._setConfig = function(config){

	this._config = {
       keyName: 'id',
       path: '',
	}

	for(var k in this._config){
    	if(config.hasOwnProperty(k)){
    		this._config[k] = config[k];
    	}
    }
}

Grid.prototype._setGridConfig = function(gridConfig){

    this._gridConfig = {
        height: 450,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        rowList: [10, 20, 30],
        colNames:[],
        colModel:[],
        pager: "#pager",
        viewrecords: false,
        caption: "",
        add: true,
	    add: true,
	    edit: false,
	    del: false,
	    search: false,
	    refresh: false,
        addtext: '添加',
        edittext: '修改',
        hidegrid: false,
        pagerpos: 'right'
    }

    for(var k in this._gridConfig){
    	if(gridConfig.hasOwnProperty(k)){
    		this._gridConfig[k] = gridConfig[k];
    	}
    }
}

Grid.prototype.initService = function(){

	var that = this;

    this.app.factory('Service', [function(){


        var methods = ['page', 'update', 'delete', 'add'];
        console.log('path' + that._config.path);
		var remote = new rpc.ServiceProxy("/services/" + that._config.path, {
			asynchronous : false,
			methods : methods
		});

      	var list = function (query, pageNo, pageSize, successCallback, errorCallback) {
    	   //var localFilter = config.filterHandler(filter);
    	    var localQuery = query;
    	    var response = remote.page(localQuery, pageNo, pageSize);
    	    successCallback(response);
        };


        var del = function(key, successCallback, errorCallback){
        	try{
	    		var response = remote['delete'](key);
	            successCallback(response);
	    	}catch(e){
	    		errorCallback(e);
	    	}	
        }

        var submit = function(item, successCallback, errorCallback) {
	    	var response;
	    	var add = false;
	    	if(item.__add){
	    		add = true;
	    	}
	    	delete item.__add;
	    	try{
	    		if(add){
	    	        remote.add(item);
	            }else{
	        	    remote.update(item);
	            }
	            successCallback(response);
	    	}catch(e){
	    		item.__add = add;
	    		errorCallback(e);
	    	}	
	    };

        var ret =  {
           list: list,
           submit: submit,
           del: del
        }

        return ret;

    }]);
}

Grid.prototype.initCtrl = function(){

    var that = this;

    this.app.controller('ctrl', ['$scope', '$compile', 'Service', function($scope, $compile, service) {
	   
	    $scope.title = "用户管理";
	    $scope.query = {};
	    $scope.item = {};

	    that.app.scope = $scope;

        $scope.pageNo = 1; 

	    $scope.pagination = {
		    pageNo: 1,
		    pageSize: 10
		};

         $scope.search = function(){
         	list();
         };

        //add row
        $scope.add = function(){
        	$scope.item = {
        		__add: true
        	}
            $('#addModal').modal();   	
        }

         //edit row
         $scope.edit = function(key){
  
            angular.forEach($scope.data, function(data){
                if(data[that._config.keyName] === key){
                    $scope.item = angular.copy(data);
                    $scope.item.__add = false;
            	}
            });
            $('#addModal').modal();
        };

        //delete row 
        $scope.delete = function(key, successCallback, errorCallback){
             
            confirm_call('确认是否删除该条记录?', function(){
                service.del(key, function(){
                    $scope.$apply(function(){
		    		    $scope.search();
			        });
                });
            })
        };

        $scope.submit = function(){
            service.submit($scope.item, function(){
	    		$('#addModal').modal("hide"); 
	    		$scope.search();
	    	}, function(e){
	    	  	alert(e);
	    	});
        }

	    var list = function () {
	        	 
            var pageNo = $scope.pagination.pageNo;
            var pageSize = $scope.pagination.pageSize;
            var query = [];
            for(var k in $scope.query){
               var value = $scope.query[k];
               if(!value){
                    continue;
               }
               var item = {
               	  column: k,
               	  value: value,
               	  operate: '='
               }
               query.push(item);
            }
            service.list({data: query}, pageNo, pageSize, function(response){
                $scope.data = response.data;
                $scope.total = response.count;
                var pageCount = ($scope.total - 1) / pageSize + 1;
                var grid = $("#" + that._tableId)[0];  
                var data = {
	              total: pageCount,
	              page: $scope.pagination.pageNo,
	              recoreds: $scope.total,
	              rows: $scope.data
	            } 
	            grid.addJSONData(data);
            }, function(){
           	    //handle error
            });
	 
	    };
	 
        // query data from server
        that._gridConfig.datatype =  function(postdata){

            if(!$scope.$$phase) {
			    $scope.$apply(function(){
			    	$scope.pagination.pageNo = postdata.page;
			    });
			}else{
				$scope.pagination.pageNo = postdata.page;
			}
        };

        that._gridConfig.gridComplete = function() {

            var objs = angular.element('.bage-action'); 
            angular.forEach(objs, function(obj){
                $compile(obj)($scope);
            });
        };

        var opCol = {
        	name: 'op', 
        	align: 'center',
        	width: 30, 
        	formatter: function(cellvalue, options, rowObject){
        		var key = rowObject[that._config.keyName];
                var edit = "<button type=\"button\" class=\"btn-xs btn-primary\" style=\"margin-left:5px;margin-right:5px;\" ng-click=edit('" + key + "')>编辑</button>";
                var del = "<button type=\"button\" class=\"btn-xs btn-danger\" style=\"margin-left:5px;margin-right:5px;\" ng-click=delete('" + key + "')>删除</button>";
                var s = edit + del ;
                return "<span class=\"bage-action\">"  + s + "</span>";
                //return s;
            }
        } 

        that._gridConfig.colModel.push(opCol);

        $("#" + that._tableId).jqGrid(that._gridConfig);

        $("#" + that._tableId).jqGrid('navGrid', '#pager', {
		    view: false,
		    add: false,
		    edit: false,
		    del: false,
		    search: false,
		    refresh: false,
		}).navButtonAdd('#pager',{
		    caption:"ADD", 
		    buttonicon:"ui-icon-plusthick", 
		    onClickButton: function(){ 
		        $scope.add();
		    }, 
		    position:"last"
		});
        
  
        $scope.$watch('pagination.pageNo + pagination.pageSize', list);

        // Add responsive to jqGrid
        $(window).bind('resize', function () {
            var width = $('.table_wrapper').width();
            $("#" + that._tableId).setGridWidth(width);
        });

    }]); 
}

Grid.prototype.init = function(){

   this.app = angular.module('app', []);

   //init angularjs service
   this.initService();

   //init angularjs controller
   this.initCtrl();
   
}
    
