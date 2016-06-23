

var Grid = function(tableId, config, gridConfig){
    this._tableId = tableId;
    this._config = config;

    this.init();
    this._setGridConfig(gridConfig);
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
        pager: "#pager_list_2",
        viewrecords: false,
        caption: "",
        add: true,
        edit: true,
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

	        var ret =  {
               list: list
	        }

	        return ret;

     }]);
}

Grid.prototype.initCtrl = function(){

    var that = this;

    this.app.controller('ctrl', ['$scope', 'Service', function($scope, service) {
	   
	    $scope.title = "用户管理";
	    $scope.query = {};

	    that.app.scope = $scope;

        $scope.pageNo = 1; 

	    $scope.pagination = {
		    pageNo: 1,
		    pageSize: 10
		};

         $scope.search = function(){
         	list();
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

	    $scope.addPage = function(){
	    	 console.log("add page");
	    	//$scope.pageNo = $scope.pageNo + 1;
	
	    	
	    }
	 
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

        $("#table_list_2").jqGrid(that._gridConfig);

        // Add selection
        $("#table_list_2").setSelection(4, true);


        // Setup buttons
        $("#table_list_2").jqGrid('navGrid', '#pager_list_2',
              {edit: true, add: true, del: true, search: true},
              {height: 200, reloadAfterSubmit: true}
        );

        $scope.$watch('pagination.pageNo + pagination.pageSize', list);

        // Add responsive to jqGrid
        $(window).bind('resize', function () {
            var width = $('.jqGrid_wrapper').width();
            $('#table_list_1').setGridWidth(width);
            $('#table_list_2').setGridWidth(width);
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
    
