// Startup Scripts
$(document).ready(function()
{
    console.log("script executed 1");
    $('.hero').css('height', ($(window).height() - $('header').outerHeight()) + 'px'); // Set hero to fill page height

    $('#scroll-hero').click(function()
    {
        $('html,body').animate({scrollTop: $("#hero-bloc").height()}, 'slow');
    });
});


// Window resize
$(window).resize(function()
{
    console.log("script executed 2");
    $('.hero').css('height', ($(window).height() - $('header').outerHeight()) + 'px'); // Refresh hero height
});