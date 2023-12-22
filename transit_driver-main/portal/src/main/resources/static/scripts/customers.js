$(document).ready(function() {

    $.ajax({
        url:"api/getlinkaccounts",
        type:"GET",
        success:function(response){
            console.log(response);
            $("#tabletitle").html("Accounts");
            let dataSet=[];

            for(let i=0; i< response.length; i++){
                let html=[];

                let id=response[i].id;
                let firstname=response[i].firstname;
                let surname=response[i].surname;
                let profilename=response[i].profilename;
                let accountholderstatus=response[i].accountholderstatus;
                let accountnumber=response[i].accountnumber;
                let branch=response[i].branch;
                let createdat=new Date(response[i].createdat).toISOString().slice(0,19).replace('T',' ');

                html.push(id);
                html.push(firstname);
                html.push(surname);
                html.push(profilename);
                html.push(accountholderstatus);
                html.push(accountnumber);
                html.push(branch);
                html.push(createdat);

                dataSet.push(html);

                if (  $.fn.DataTable.isDataTable( '#table_list' ) ) {
                    $('#table_list').DataTable().clear().destroy();
                }
                data_table= $('#table_list').DataTable( {
                    data: dataSet,
                    dom: 'Bfrtip',
                    columns: [           
                        { title: "#" },
                        { title: "First Name"},
                        { title: "Surname" },
                        { title: "Profile Name" },
                        { title: "Status"},
                        { title: "Account Number"},
                        { title: "Branch"},
                        { title: "Date Linked" }
                    ],
                    buttons:[
                        'excelHtml5',
                        'pdfHtml5'
                    ],
                    
                } );

            }
        }
    })
})
