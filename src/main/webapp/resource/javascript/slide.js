var btnNext=document.getElementById('btn__slide--next');
var slideShowNext=function(){
    var slideList=document.getElementsByClassName('slide__item'); 
    var n=slideList.length;
    for(var i=0;i<n;i++){
        if(slideList[i].classList.contains('slide__item--active')){
            slideList[i].classList.remove('slide__item--active');
            var idx= (i==n-1) ? 0 : i+1;
            slideList[idx].classList.add('slide__item--active');
            break;
        }
    }
}
btnNext.onclick=function(){
    slideShowNext();
}
var btnPrev=document.getElementById('btn__slide--prev');
btnPrev.onclick=function(){
    var slideList=document.getElementsByClassName('slide__item'); 
    var n=slideList.length;
    for(var i=0;i<n;i++){
        if(slideList[i].classList.contains('slide__item--active')){
            slideList[i].classList.remove('slide__item--active');
            var idx= (i==0) ? n-1 : i-1;
            slideList[idx].classList.add('slide__item--active');
            break;
        }
    }
}
setInterval(function(){
    slideShowNext();
},3000)

