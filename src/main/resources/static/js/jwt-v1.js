function UserForm(){
    this.showOU();
}

UserForm.prototype = {
    showOU:function(){
        var $this = this;
        $(".scimRadio").change(function(){
           var test = this.attr("checked");
            alert(this);
        });
    }
}

/**
 * displaytag paginated use it.
 * Don't change it
 *
 * @param formId
 * @param data
 */
function displaytagform(formId, data) {
    var $form = $("#" + formId);
    var action = $form.attr("action");
    var params = action.indexOf('?') == -1 ? '?' : '&';
    $.map(data, function (d) {
        params += (d.f + "=" + d.v + "&");
    });

    var url = action + params;
    var $targetDiv = $("div.displayTarget");
    if ($targetDiv.length > 0) {
        //if exist, load  the content to the div
        $targetDiv.load(url);
    } else {
        location.href = url;
    }
}

function indexPage() {
    this.init()
}
indexPage.prototype={
    init:function () {
        this.getAppdata()
    },
    getAppdata:function () {
        $.get(contextPath+'/system/splogin/load',function (data) {
            if(data.length>0){
                var str = ''
                $.each(data,function (index,val) {
                    str+= '<div class="col-sm-2 ui-appList-box ui-sortable-handle" >' +
                        '                        <a class="ui-appList" href="'+val.spLoginUrl+'" title="'+val.spName+'" target="_blank">' +
                        '                            <span class="ui-appList-icon bg-white"><img src="'+contextPath+'/static/images/logo.png"></span>' +
                        '                            <p class="ui-appList-text  info-box-text-bookmark">' +val.spName+
                        '                            </p>' +
                        '                            <div class="ui-appList-set">' +
                        '                            <span class="ui-appList-set-icon bookmark-icon warning-box-number"  >' +
                        '                                <i class="glyphicon glyphicon-share-alt" ></i>' +
                        '                            </span>' +
                        '                            </div>' +
                        '                        </a>' +
                        '                    </div>'
                })
                $('.app-sortable-list').html(str)
            }else {
                str = '<p class="alert alert-warning">暂无应用，请到系统设置中SPLoginUrl处添加</p>'
                $('#ssoBox').html(str)
            }
        })
    }

}