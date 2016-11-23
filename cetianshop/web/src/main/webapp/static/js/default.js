/**
 * Created by Alcott Hawk on 11/21/2016.
 */
jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
$("#li").click(function(){
    var slidebar = $(".mobile");
    change(slidebar)
})

$(".content").click(function(){
    var slidebar = $(".mobile");
    change(slidebar)
})

function change(js) {
    var slidebar = js;
    var t = slidebar.css("display");
    if (t == "none"){
        slidebar.css("display","block");
    } else {
        slidebar.css("display","none");
    }
}
(function($) {
    $.expr[":"].Contains = function(a, i, m) {
        return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
    };
    /*function filterList(header, list) {
     //@header 头部元素
     //@list 无需列表
     //创建一个搜素表单
     var form = $("<form>").attr({
     "class":"filterform",
     action:"#"
     }), input = $("<input>").attr({
     "class":"filterinput",
     type:"text"
     });
     $(form).append(input).appendTo(header);
     $(input).change(function() {
     var filter = $(this).val();
     if (filter) {
     $matches = $(list).find("a:Contains(" + filter + ")").parent();
     $("li", list).not($matches).slideUp();
     $matches.slideDown();
     } else {
     $(list).find("li").slideDown();
     }
     return false;
     }).keyup(function() {
     $(this).change();
     });
     }
     $(function() {
     filterList($("#form"), $("#demo-list"));
     });*/
})(jQuery);

$(function(){
    //顶部导航切换
    $("#demo-list li").click(function(){
        $("#demo-list li.active").removeClass("active")
        $(this).addClass("active");
    })
})