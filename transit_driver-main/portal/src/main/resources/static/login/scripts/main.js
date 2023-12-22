$(document).ready(function(){

$('#logoutModal').on('shown.bs.modal',function(){
  
    $(".logoutbtn").click(function(){
       let view="logout";

        $.ajax({
            url:"../controllers/logincontroller.php",
            type:"POST",
            data:{
                action:view
            },
            success:function(data){
                console.log(data);
                window.location.replace("../index.php");
            }
        });  
    });    
});

})