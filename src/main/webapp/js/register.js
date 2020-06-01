var lFollowX = 0,
lFollowY = 0,
x = 0,
y = 0,
friction = 1 / 15;

function moveBackground() {
    x += (lFollowX - x) * friction;
    y += (lFollowY - y) * friction;

    translate = 'translate(' + x + 'px, ' + y + 'px) scale(1.1)';

    $('.bg').css({
    '-webit-transform': translate,
    '-moz-transform': translate,
    'transform': translate
    });

    window.requestAnimationFrame(moveBackground);
}

$(window).on('mousemove click', function(e) {
    var lMouseX = Math.max(-100, Math.min(100, $(window).width() / 3 - e.clientX));
    var lMouseY = Math.max(-100, Math.min(100, $(window).height() / 2 - e.clientY));
    lFollowX = (25 * lMouseX) / 100; // 100 : 12 = lMouxeX : lFollow
    lFollowY = (30 * lMouseY) / 100;
});
$('#register-button').click(function(){
    $('#register-button').fadeOut("slow",function(){
      $("#container").fadeIn();
    });    
  });

  $(".close-btn").click(function(){
    $("#container").fadeOut(800, function(){
      $("#register-button").fadeIn(800);
    });
    $("#audio_bg").html("<audio  id='bgMusic'  autoplay loop ></audio>");
  });


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



