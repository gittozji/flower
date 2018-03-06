$(document).ready(function(){
    $(".button-collapse").sideNav();

    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 15,
        'format':'yyyy-mm-dd'
    });

    // 监听导航栏是否获取焦点
    $(document).on("mouseover mouseout",".main-container-nav",function(e){
        if(e.type == "mouseover"){
            $(this).css("overflow","auto");
        }else{
            $(this).css("overflow","hidden");
        }
    });

    $('#suceessModal').modal({dismissible: false});
});