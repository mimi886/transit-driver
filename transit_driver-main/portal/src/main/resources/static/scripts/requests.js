$(document).ready(function() {

    getRequests();


    function getRequests(){
        $.ajax({
            url:"requests/getRequests",
            type:"GET",
            success:function(response){
                console.log(response);
              

               
            }
        })
    }


})