$(document).ready(function() {

    const loginForm = document.getElementById('login_form');

    //login
    function login(event) {
        event.preventDefault();
        let action = "login";
        const inputs = loginForm.elements;
        let username = inputs['username'].value;
        let password = inputs['password'].value;

        $.ajax({
            url: "controllers/loginController.php",
            method: "POST",
            data: {
                action: action,
                username: username,
                password: password
            },
            success: function(data) {
                console.log(data)
                if(data.trim() == "success"){
                    toastr["success"]("successful", "login");
                    $('#login_form').trigger('reset');
                    localStorage.clear();
                    window.location.replace("views/dashboard.php");
                }
                else{
                    toastr["error"]("unsuccessful", "login");
                    $('#login_form').trigger('reset');
                }
            }
        });
    }

    //Event Listeners
    loginForm.addEventListener("submit", login);
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "1000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
      }

})