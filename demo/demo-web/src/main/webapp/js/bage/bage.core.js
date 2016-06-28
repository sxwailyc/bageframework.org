
var Bage = {

}

Bage.confirm_call = function(content, confirmCallback, cancelCallback){
     $.confirm({
        title: '确认!',
        content: content,
        confirmButton: '确认',
        cancelButton: '取消',
        confirmButtonClass: 'btn-danger',
        cancelButtonClass: 'btn-info',
        confirm: confirmCallback,
        cancel: cancelCallback
     });  
};

Bage.errorMsg = function(content){
    $.alert({
      icon: 'fa fa-warning',
      title: '错误',
      content: content,
      columnClass: 'col-md-6 col-md-offset-3',
      confirmButton: '确认',
      confirmButtonClass: 'btn-info'
    });
}

Bage.successMsg = function(content){
    $.alert({
      title: '提示',
      content: content,
      columnClass: 'col-md-6 col-md-offset-3',
      confirmButton: '确认',
      confirmButtonClass: 'btn-info'
    });
}

