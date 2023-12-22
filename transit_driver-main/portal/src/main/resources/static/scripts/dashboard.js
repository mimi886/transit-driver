$(document).ready(function() {
    
    $("#linkaccountform").submit(function(e){
        e.preventDefault();

        let msinum=$("#msinum").val();
        let accnum=$("#accnum").val();

        if(msinum =="" ||accnum =="" ){
            toastr.error('Empty fields not allowed');
        }else{

                $.ajax({
                    url:"api/linkaccount",
                    data:{
                        "msinum":msinum,
                        "accnum":accnum,
                    },
                    method: "POST",
                    beforeSend:function(){
                        $("#linkbtn").prop('disabled','disabled');
                    },
                    success:function(response){
                        console.log(response.success)
                        if(response.success == "true"){
                            toastr.success("Link Account",response.message);
                        }
                        else if(response.success == "false"){
                            toastr.error("Link Account",response.message);
                        }else{
                            toastr.error('Response body empty');
                        }
                        $("#linkbtn").prop('disabled','');
                        $("#linkaccountform")[0].reset();
                    },
                    error:function(){
                        toastr.error('An error occurred.');
                        $("#linkbtn").prop('disabled','');
                    }

                })
            }
    
    })

    $("#searchForm").submit(function(e){
        e.preventDefault();

        let acctnum=$("#acctnum").val();

        if(acctnum =="" ){
            toastr.error('Account number is empty');
        }else{

                $.ajax({
                    url:"api/searchaccount",
                    data:{
                        "acctnum":acctnum,
                    },
                    method: "POST",
                    beforeSend:function(){
                        $("#searchbtn").prop('disabled','disabled');
                    },
                    success:function(response){
                        console.log(response)
                        if(Object.keys(response).length === 0 && response.constructor === Object){
                            toastr.info('No Account Found');
                            $("#account_details").html("");
                        }else{
                            let html=`<table class="table">
                                            <thead>
                                            <th scope="col">Account Number</th>
                                            <th scope="col">Profile Name</th>
                                            <th scope="col">Customer Number</th>
                                            </thead>
                                            <tbody>
                                                <td scope="row">${response.accountnumber}</td>
                                                <td scope="row">${response.profilename}</td>
                                                <td scope="row">${response.custnum}</td>
                                            </tbody>
                                        </table>`;
                            $("#account_details").html(html);
                        }
                       
                        $("#searchbtn").prop('disabled','');
                        $("#searchForm")[0].reset();
                    },
                    error:function(){
                        toastr.error('An error occurred.');
                        $("#searchbtn").prop('disabled','');
                    }

                })
            }
    })


    $("#linkaccountscard").on('click',function(){
        $.ajax({
            url:"api/getlinkaccounts",
            type:"GET",
            success:function(response){
                //console.log(response);
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



    $("#today").on('click',function(){
        $.ajax({
            url:"api/gettransactionstoday",
            type:"GET",
            success:function(response){
                //console.log(response);
                $("#tabletitle").html("Transactions | Today");
                let dataSet=[];

                for(let i=0; i< response.length; i++){
                    let html=[];

                    let internaltransactionid=response[i].internaltransactionid;
                    let accountnumber=response[i].accountnumber;
                    let transactionType=response[i].transactionType;
                    let externaltransactionid=response[i].externaltransactionid;
                    let status=response[i].status;
                    let fcubref=response[i].fcubref;
                    let branchcode=response[i].branchcode;
                    let createdat=new Date(response[i].createdat).toISOString().slice(0,19).replace('T',' ');

                    html.push(internaltransactionid);
                    html.push(accountnumber);
                    html.push(branchcode);
                    html.push(transactionType);
                    html.push(status);
                    html.push(fcubref);
                    html.push(externaltransactionid);
                    html.push(createdat);

                    dataSet.push(html);

                    if (  $.fn.DataTable.isDataTable( '#table_list' ) ) {
                        $('#table_list').DataTable().clear().destroy();
                    }
                    data_table= $('#table_list').DataTable( {
                        data: dataSet,
                        dom: 'Bfrtip',
                        columns: [           
                            { title: "Transaction ID" },
                            { title: "Account Number"},
                            { title: "Branch Code" },
                            { title: "Type" },
                            { title: "Status"},
                            { title: "CBA Reference"},
                            { title: "MTN Reference"},
                            { title: "Date" }
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


    $("#month").on('click',function(){
        $.ajax({
            url:"api/gettransactionsmonth",
            type:"GET",
            success:function(response){
                //console.log(response);
                $("#tabletitle").html("Transactions | month");
                let dataSet=[];

                for(let i=0; i< response.length; i++){
                    let html=[];

                    let internaltransactionid=response[i].internaltransactionid;
                    let accountnumber=response[i].accountnumber;
                    let transactionType=response[i].transactionType;
                    let externaltransactionid=response[i].externaltransactionid;
                    let status=response[i].status;
                    let fcubref=response[i].fcubref;
                    let branchcode=response[i].branchcode;
                    let createdat=new Date(response[i].createdat).toISOString().slice(0,19).replace('T',' ');

                    html.push(internaltransactionid);
                    html.push(accountnumber);
                    html.push(branchcode);
                    html.push(transactionType);
                    html.push(status);
                    html.push(fcubref);
                    html.push(externaltransactionid);
                    html.push(createdat);

                    dataSet.push(html);

                    if (  $.fn.DataTable.isDataTable( '#table_list' ) ) {
                        $('#table_list').DataTable().clear().destroy();
                    }
                    data_table= $('#table_list').DataTable( {
                        data: dataSet,
                        dom: 'Bfrtip',
                        columns: [           
                            { title: "Transaction ID" },
                            { title: "Account Number"},
                            { title: "Branch Code" },
                            { title: "Type" },
                            { title: "Status"},
                            { title: "CBA Reference"},
                            { title: "MTN Reference"},
                            { title: "Date" }
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



    
})