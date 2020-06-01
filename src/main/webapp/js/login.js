$('#login-button').click(function(){
    $('#login-button').fadeOut("slow",function(){
      $("#container").fadeIn();
    });
  });
  
  $(".close-btn").click(function(){
    $("#container, #read-container").fadeOut(800, function(){
      $("#login-button").fadeIn(800);
    });
  });

  $('#read').click(function(){
    $("#container").fadeOut(function(){
      $("#read-container").fadeIn();
    });
  });

  $('#agree').click(function(){
    $('#read-container').fadeOut("slow",function(){
      $("#container").fadeIn();
    });
  });

  $(function(){
    $(window).on('load', function() {
      $('#preloader').addClass("loaded");
    });
  })


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
