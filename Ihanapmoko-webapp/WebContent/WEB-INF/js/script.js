// JavaScript Document

$("#thumbnailsProduct a").on('click', function(e) {
    e.preventDefault();

    $('#big').fadeOut().attr('src', $(this).attr('href')).fadeIn();
});