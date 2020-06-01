
$(function(){
    $(".navmenu li a").click(function(){
        $(this).next("ul").slideToggle();
        // 点击显示子菜单
    })

    $(".header .menu").click(function(){
        console.log(123);
        $(".navmenu").addClass("on");
        $(".changeBg").fadeIn(300);
        // 背景淡入
    })

    $(".changeBg").click(function(){
        $(".navmenu").removeClass("on");
        $(".changeBg").fadeOut(300);
        //背景淡出
    })
})