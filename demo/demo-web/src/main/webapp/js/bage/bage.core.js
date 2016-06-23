

var confirm_call = function(content, confirmCallback, cancelCallback){
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