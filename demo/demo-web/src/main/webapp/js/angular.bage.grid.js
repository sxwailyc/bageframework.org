

var Grid = function(tableId, config, jqGridConfig){
    this.tableId = tableId;
    this.config = config;
    this.jqGridConfig = jqGridConfig;
    
    this.init();
}

Grid.prototype.initService = function(){

    

}

Grid.prototype.initCtrl = function(){

   var that = this;

   this.app.controller('ctrl', function($scope) {
	   
	   $scope.title = "我是标题";

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
	   
	   // Examle data for jqGrid
        var mydata = [
            {id: "1", invdate: "2010-05-24", name: "test", note: "note", tax: "10.00", total: "2111.00"} ,
            {id: "2", invdate: "2010-05-25", name: "test2", note: "note2", tax: "20.00", total: "320.00"},
            {id: "3", invdate: "2007-09-01", name: "test3", note: "note3", tax: "30.00", total: "430.00"},
            {id: "4", invdate: "2007-10-04", name: "test", note: "note", tax: "10.00", total: "210.00"},
            {id: "5", invdate: "2007-10-05", name: "test2", note: "note2", tax: "20.00", total: "320.00"},
            {id: "6", invdate: "2007-09-06", name: "test3", note: "note3", tax: "30.00", total: "430.00"},
            {id: "7", invdate: "2007-10-04", name: "test", note: "note", tax: "10.00", total: "210.00"},
            {id: "8", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "21.00", total: "320.00"},
            {id: "9", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "11", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "12", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "13", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "14", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "15", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "16", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "17", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "18", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "19", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "21", invdate: "2007-10-01", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "22", invdate: "2007-10-02", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "23", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "24", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "25", invdate: "2007-10-05", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "26", invdate: "2007-09-06", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"},
            {id: "27", invdate: "2007-10-04", name: "test", note: "note", amount: "200.00", tax: "10.00", total: "210.00"},
            {id: "28", invdate: "2007-10-03", name: "test2", note: "note2", amount: "300.00", tax: "20.00", total: "320.00"},
            {id: "29", invdate: "2007-09-01", name: "test3", note: "note3", amount: "400.00", tax: "30.00", total: "430.00"}
        ];


        var getData = function(postdata){
            var thegrid = $("#" + that.tableId)[0];  
            var data = {
              total: 10,
              page: postdata.page,
              recoreds: 10,
              rows: mydata
            } 
            thegrid.addJSONData(data);
        }

        // Configuration for jqGrid Example 2
        $("#table_list_2").jqGrid({
            //data: mydata,
            datatype: getData,
            height: 450,
            autowidth: true,
            shrinkToFit: true,
            rowNum: 20,
            rowList: [10, 20, 30],
            colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],
            colModel:[
                {name:'id',index:'id', editable: true, width:60, sorttype:"int",search:true},
                {name:'invdate',index:'invdate', editable: true, width:90, sorttype:"date", formatter:"date"},
                {name:'name',index:'name', editable: true, width:100},
                {name:'amount',index:'amount', editable: true, width:80, align:"right",sorttype:"float", formatter:"number"},
                {name:'tax',index:'tax', editable: true, width:80, align:"right",sorttype:"float"},
                {name:'total',index:'total', editable: true, width:80,align:"right",sorttype:"float"},
                {name:'note',index:'note', editable: true, width:100, sortable:false}
            ],
            pager: "#pager_list_2",
            viewrecords: true,
            caption: "Example jqGrid 2",
            add: true,
            edit: true,
            addtext: '添加',
            edittext: '修改',
            hidegrid: false
        });

        // Add selection
        $("#table_list_2").setSelection(4, true);


        // Setup buttons
        $("#table_list_2").jqGrid('navGrid', '#pager_list_2',
                {edit: true, add: true, del: true, search: true},
                {height: 200, reloadAfterSubmit: true}
        );

        // Add responsive to jqGrid
        $(window).bind('resize', function () {
            var width = $('.jqGrid_wrapper').width();
            $('#table_list_1').setGridWidth(width);
            $('#table_list_2').setGridWidth(width);
        });

    }); 
}

Grid.prototype.init = function(){

   this.app = angular.module('app', []);

   //init angularjs controller
   this.initCtrl();
   
}
    
