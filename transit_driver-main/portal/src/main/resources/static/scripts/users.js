$(document).ready(function() {
    getUsers();

    $.ajax({
        type: "POST",
        url: "branches/all-branches",
        cache: false,
        success: function(response){
            //console.log(response);
            if(!(response==null)){
                let dataSet=[];

                let data=JSON.parse(response);
                let html=`<option selected disabled>-- Select one --</option> `;

                for(let i=0;i<data.length;i++){

                    html+=`<option value="${data[i].branchcode}">${data[i].branchname}</option>`;

                }
                $("#branch").html(html);
                $("#up_branch").html(html);

            }
        }
    })
   

    $("#new_user_form").submit(function(e){
        e.preventDefault();
          let fullname=$("#fullname").val();
          let email=$("#email").val();
          let role=$("#role option:selected").val();
          let phone=$("#phone").val();
          
          $.ajax({
          url:"users/new-user",
          data:{
            "fullname":fullname,
            "email" :email,
            "role":role,
            "phone":phone,
          } , 
          method:"POST",
          beforeSend:function(){  
                   $('#save_btn').prop('disabled','disabled');  
               },
          success:function(response){  
            //console.log(response);
            document.getElementById("save_btn").disabled=false;
            getUsers();
            if(!(response==null)){
                if(response.success){
                    toastr.success('Saved Successfully'); 
                    $("#new_user_form")[0].reset();                    
                }else{
                    toastr.error('User with email already exist'); 
                    $("#new_user_form")[0].reset();
                }
            }else{
                toastr.error('Error occured'); 
                $("#new_user_form")[0].reset();
            }
          } 

          });  
      });

      $("#updateUser_form").submit(function(e){
        e.preventDefault();
          let fullname=$("#up_fullname").val();
          let email=$("#up_email").val();
          let role=$("#up_role option:selected").val();
          let phone=$("#up_phone").val();
          let userid=$("#userid").val();
          let branch=$("#up_branch option:selected").val();
         
          $.ajax({
          url:"users/update-user",
          data:{
            "fullname":fullname,
            "email" :email,
            "role":role,
            "phone":phone,
            "id":userid,
            "branch":branch
          } ,   
          method:"POST",
          beforeSend:function(){  
                   $('#save_btn').prop('disabled','disabled');  
               },
          success:function(response){  
            console.log(response);
            document.getElementById("save_btn").disabled=false;
            getUsers();
            if(!(response==null)){
              let results=JSON.parse(response);
                console.log(results.error)
                if(results.error=="true"){
                    toastr.error('User with email already exist'); 
                }else{
                    toastr.success('Saved Successfully'); 
                    $("#new_user_form")[0].reset();
                }
            }
            $("#updateUserModal").modal('hide');
          } 

          });  
      });

      $('#table_list').on('click','.activate',function(){
        let id = $(this).prop('id');
        $.ajax({
            type: "POST",
            url: "users/activate-user",
            data:{
                id:id
            },
            success: function(response){
                getUsers();
               
                if(!(response==null)){
          
                    if(response.success){
                        toastr.success('User Account Activated'); 
                    }
                }
            }
        }) 

      })

      $('#table_list').on('click','.deactivate',function(){
        let id = $(this).prop('id');
        $.ajax({
            type: "POST",
            url: "users/deactivate-user",
            data:{
                id:id
            },
            success: function(response){
                getUsers();
               
                if(!(response==null)){
                    
                    if(response.success){
                        toastr.warning('User Account Deactivated'); 
                    }
                }
            }
        }) 

      })

      $('#table_list').on('click','.update',function(){
        let id = $(this).prop('id');
        let fullname=$(this).attr('data-fullname');
        let email=$(this).attr('data-email');
        let role=$(this).attr('data-role');
        let phone=$(this).attr('data-phonenumber');
        let branch=$(this).attr('data-branch');
        $("#userid").val(id);
        $("#up_fullname").val(fullname);
        $("#up_email").val(email);
        document.getElementById("up_role").value=role;
        $("#up_phone").val(phone);
        const select_branch=document.querySelector("#up_branch");
        select_branch.value=branch;

        $("#updateUserModal").modal('show');

      })

      function getUsers(){
        $.ajax({
            type: "POST",
            url: "users/all-users",
            cache: false,
            success: function(response){
                console.log(response);
                if(!(response==null)){
                    let dataSet=[];

                    for(let i=0;i<response.length;i++){
                        console.log(response[i].id);
                        let html = [];
                        let id=response[i].id; 
                        let name=response[i].fullname;
                        let email= response[i].email;
                        let role= response[i].role;
                        let active=response[i].status;
                        let phonenumber=response[i].phonenumber;

                 
                        let action="";
                    

                        if(active){
                            activeBadge=`<span class="badge bg-success">Active</span>`;
                            action=`<a class="btn btn-danger btn-sm deactivate m-2" href="#!"  id="${response[i].id}"><i class="bi bi-lock-fill"></i></a>
                            <a class="btn btn-primary btn-sm update" href="#!"  id="${response[i].id}" data-fullname="${response[i].fullname}" data-email="${response[i].email}" data-role="${response[i].role}" data-phonenumber="${response[i].phonenumber}" ><i class="bi bi-pencil-square"> </i></a>
                            `;
                        }
                        else {
                            activeBadge=`<span class="badge bg-warning">Inactive</span>`; 
                            action=`<a class="btn btn-success btn-sm activate m-2" href="#!"  id="${response[i].id}"><i class="bi bi-unlock-fill"></i></a>
                            <a class="btn btn-primary btn-sm update" href="#!"  id="${response[i].id}" data-fullname="${response[i].fullname}" data-email="${response[i].email}" data-role="${response[i].role}" data-phonenumber="${response[i].phonenumber}" d><i class="bi bi-pencil-square"> </i></a>
                            `;
                    }
    
                        html.push(id);
                        html.push(name);
                        html.push(email);
                        html.push(role);
                        html.push(activeBadge);
                        html.push(phonenumber);
                        html.push(action);
                        
                        dataSet.push(html);
                    }

                    if (  $.fn.DataTable.isDataTable( '#table_list' ) ) {
                        $('#table_list').DataTable().clear().destroy();
                    }
    
                    data_table= $('#table_list').DataTable( {
                        data: dataSet,
                        dom: 'Bfrtip',
                        columns: [           
                            { title: "#" },
                            { title: "Full Name" },
                            { title: "Email" },
                            { title: "Role" },
                            { title: "Active" },
                            { title: "Phone Number"},
                            { title: "Action" },
                        ],
                        buttons:[
                            'excelHtml5',
                            'pdfHtml5'
                        ]
                    } );
                }
            }
        })
      }


})