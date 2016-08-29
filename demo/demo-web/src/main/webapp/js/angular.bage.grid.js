

var Button = function(label, css, style, ngClick, show){
   this._label = label;
   this._css= css;
   this._style = style;
   this._ngClick = ngClick;
   this._show = show;
}

Button.prototype.render = function(row){

    if(this._show && !this._show(row)){
        return "";
    }

    var s = '<button type="button"';
    if(this._css){
        s = s + ' class="' + this._css + '"';
    }

    if(this._style){
        s = s + ' style="' + this._style + '"';
    }
    s = s + ' ng-click=' + this._ngClick + '>' + this._label + '</button>';
    return s;
}

var Grid = function(tableId, config, gridConfig){
 
    this._tableId = tableId;
    this._setConfig(config);
    this._setGridConfig(gridConfig);
    this._buttons = new Array();
}

Grid.prototype.addButton = function(label, css, style, ngClick, show){
    var btn = new Button(label, css, style, ngClick, show);
    this._buttons.push(btn);
}

Grid.prototype._setConfig = function(config){

	this._config = {
       keyName: 'id',
       path: '',
       buttons: undefined,
       editable: true,
       removeable: true,
       functions: {},
       services: function(service, remote){},
       controllers: function($scope, service){},
       filters: function(app, $scope){},
       datas: function($scope){},
       afterEdit: function(data){},
	}

	for(var k in this._config){
    	if(config.hasOwnProperty(k)){
    		this._config[k] = config[k];
    	}
    }

    this._config.remoteMethods = ['page', 'update', 'delete', 'add'];

    for(var i = this._config.functions.length; i--;){
        var funName = this._config.functions[i];
        this._config.remoteMethods.push(funName);
    }
        
}

Grid.prototype._setGridConfig = function(gridConfig){

    this._gridConfig = {
        height: 550,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        rowList: [10, 20, 30],
        //colNames:[],
        colModel:[],
        pager: "#pager",
        viewrecords: false,
        caption: "",
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

        console.log('path' + that._config.path);
		var remote = new rpc.ServiceProxy("/services/" + that._config.path, {
			asynchronous : false,
			methods : that._config.remoteMethods
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

        var saveCall = function(fun, successCallback){
            try{
                var ret = fun();
                successCallback(ret);
            }catch(e){
                Bage.errorMsg(e.message);
            }
        }

        var ret =  {
           list: list,
           submit: submit,
           del: del,
           saveCall: saveCall
        }

        //add funciton
        that._config.services(ret, remote);

        return ret;

    }]);
}

Grid.prototype.initCtrl = function(){

    var that = this;

    this.app.controller('ctrl', ['$scope', '$filter', '$compile', 'Service', function($scope, $filter, $compile, service) {
	   
	    $scope.title = "";
	    $scope.query = {};
	    $scope.item = {};

	    that.app.scope = $scope;

        $scope.pageNo = 1; 

	    $scope.pagination = {
		    pageNo: 1,
		    pageSize: that._gridConfig.rowNum
		};

         $scope.search = function(){
         	list();
         };

        //add row
        $scope.add = function(){
        	if(!$scope.$$phase) {
			    $scope.$apply(function(){
			    	$scope.item = {
        		        __add: true
        	        }
			    });
			}else{
				$scope.item = {
        		    __add: true
        	    }
			}
            $('#addModal').modal();   	
        }

         //edit row
         $scope.edit = function(key){
            angular.forEach($scope.data, function(data){
                if(data[that._config.keyName] == key){
                    $scope.item = angular.copy(data);
                    $scope.item.__add = false;
                    that._config.afterEdit($scope.item); 
                }
            });
            $('#addModal').modal();
        };

        $scope.getItem = function(key){
            var item ;
            angular.forEach($scope.data, function(data){
                if(data[that._config.keyName] == key){
                   item = data;
                }
            });
            return item;
        }

        //delete row 
        $scope.delete = function(key, successCallback, errorCallback){
             
            Bage.confirm_call('确认是否删除该条记录?', function(){
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

        if(that._config.editable){
            that.addButton("编辑", "btn-xs btn-primary", "margin-left:5px;margin-right:5px;", "edit('$id')");
        }

        if(that._config.removeable){
            that.addButton("删除", "btn-xs btn-danger", "margin-left:5px;margin-right:5px;", "delete('$id')");
        }

        var opCol = {
        	name: 'op', 
        	align: 'center',
        	width: 10, 
        	formatter: function(cellvalue, options, rowObject){
        		var key = rowObject[that._config.keyName];
                var s = "";
                for(var i=0; i<that._buttons.length;i++){
                    s = s + that._buttons[i].render(rowObject);
                }
                s = s.replace(/\$id/g, key);
                return "<span class=\"bage-action\">"  + s + "</span>";
            }
        } 


        //add funciton
        that._config.controllers($scope, service);
        
        //add data
        that._config.datas($scope);

        for(var i = that._gridConfig.colModel.length; i--;){
            var formatter = that._gridConfig.colModel[i].formatter;
            if(formatter && typeof formatter != 'function' && formatter.startsWith("ng:")){
                var filters = formatter.replace("ng:", "")
                that._gridConfig.colModel[i].formatter = (function(filters){
                    return function(cellvalue, options, rowObject){
                        var list = filters.split("|");
                        for(var ind in list){
                            var filter = list[ind];
                            var filterName;
                            var params = [cellvalue]
                            var tokenInd = filter.indexOf(":");
                            if(tokenInd>0){
                                filterName = filter.substring(0, tokenInd);
                                var param = filter.substring(tokenInd+1);
                                param = param.replace(/\"/g, "");
                                params.push(param);
                            }else{
                                filterName = filter;
                            }
                            return $filter(filterName).apply(null, params);
                        }
                    }
                })(filters);
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

        that.app.$scope = $scope;

    }]); 
}

Grid.prototype.init = function(){

    this.app = angular.module('app', []);

    //add filter
    this._config.filters(this.app);

    //init angularjs service
    this.initService();

    //init angularjs controller
    this.initCtrl();
   
}
    
