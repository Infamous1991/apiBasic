
$(document).ready(function() {
    $('#newEmployee').click( e => {
        var employee= {
            id : 0,
            f_name: $('#f_name').val(),
            l_name: $('#l_name').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            hire_date: $('#hire_date').val() + 'T00:00:00',
            job_id: $('#job_id').val(),
            salary: $('#salary').val(),
            manager_id: $('#manager_id').val(),
            department_id: $('#department_id').val()
        }
        console.log(employee)
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/employee/new",
            contentType: 'application/json',
            data: JSON.stringify(employee),
            success: (response) => {
                console.log(response)
                showNewEmployee(response)
            }
        })
    } )
})

function showNewEmployee(id){
    $.ajax({
        type: 'GET',
        url: `http://localhost:8080/employee/get?id=${id}`,
        datatype: 'application/json',
        success: (response) => {
            $('#result').html(
                `
                id : ${response.id} <br>
                f_name: ${response.f_name} <br>
                l_name: ${response.l_name} <br>
                email: ${response.email} <br>
                phone: ${response.phone} <br>
                hire_date: ${response.hire_date} <br>
                job_id: ${response.job_id} <br>
                salary: ${response.salary} <br>
                manager_id: ${response.manager_id} <br>
                department_id: ${response.department_id}
                `
            )
        }
    })
}