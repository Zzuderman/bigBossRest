jQuery(async function () {
    await getTableWithUsers();
})

//Интересный способ сразу вынести отдельно для каждого метода структуру и адрес по контроллеру. Здесь сделаю так, а дальше в каждом методе буду писать уже внутри, чтобы не запутаться.

const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },

    // Функция относительно действия с юзерами и далее метод и адрес.
    allUsers: async () => await fetch('rest'),

}

// Получаем таблицу всех юзеров с помощью RESTконтроллера и Jason
async function getTableWithUsers() {
    let table = jQuery('#myTable tbody');
    table.empty();

    await userFetchService.allUsers()
        .then(res => res.json())
        .then(users => {
            let text = ''
            users.forEach(user => {
                let allUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.email}</td>
                            <td>${user.age}</td>                          
                            <td> 
                                ${user.roles.length == 0 ? "[ no roles ]" : user.roles.length > 1 ?  "[" + user.roles[0].name + ", " + user.roles[1].name + "]" : "[" + user.roles[0].name + "]"}                         
                            </td>
                                                                                                               
                             <td>
                                <button class="btn btn-info" data-toggle="modal" data-target="#edit${user.id}">Edit</button>
                                <div class="modal fade" id="edit${user.id}" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true"> 
                                <div class="modal-dialog"> 
                                <div class="modal-content"> 
                                <div class="modal-header"> 
                                <h4 class="modal-title fs-5" id="editModalLabel">Edit user</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"> 
                                <span aria-hidden="true">&times;</span>
                                </button>
                                </div> 
                                 
                                <div class="modal-body">
                                 <div class="row justify-content-center align-items-center h-100">
                                        <div class="form-group text-center font-weight-bold">
                                        
                                            <label for="edit-id">ID: </label>
                                            <input type="text" class="form-control"  id="edit-id${user.id}" disabled value="${user.id}" readonly/>
                                            <br/>
                                            
                                             <label for="edit-name${user.id}">Name: </label>
                                             <input type="text" class="form-control"  id="edit-name${user.id}" value="${user.name}"/>
                                             <br/>
                                             
                                            <label for="edit-surname${user.id}">Surname: </label>
                                            <input type="text" class="form-control"  id="edit-surname${user.id}" value="${user.surname}"/>
                                            <br/>
                                            
                                            <label for="edit-email${user.id}">Email: </label>
                                            <input type="email" class="form-control"  id="edit-email${user.id}" value="${user.email}"/>
                                            <br/>
                                            
                                            <label for="edit-password${user.id}">Password: </label>
                                            <input type="text" class="form-control "  id="edit-password${user.id}" value="${user.password}"/>
                                            <br/>
                                            
                                            <label for="edit-age${user.id}">Age: </label>
                                            <input type="text" class="form-control"  id="edit-age${user.id}" value="${user.age}"/>
                                            <br/>

                                            <label for="edit-roles${user.id}">Role:</label>
                                            <select class="custom-select " size="2" multiple type="checkbox" id="edit-roles${user.id}">
                                                
                                         
                                                 <option selected value = ${user.roles[0].id}> ${user.roles[0].name}</option>
                                                 <option selected value = ${user.roles.length > 1 ? user.roles[1].id :
                                                    user.roles[0].id === 1 ? 2 : 1}>
                                                 
                                                 ${user.roles.length > 1 ? user.roles[1].name :
                                                    user.roles[0].id === 1 ? "ROLE_ADMIN" : "ROLE_USER"}
                                                 
                                                 </option>  
                                                 
                                            </select>

                                            <br/>
                                             <br/>

                                        </div>
                                        </div>

                                        <div class="modal-footer" >
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        
                                         <input class="btn btn-primary" type="submit" onclick="edit(${user.id})" value="Edit"/>                                           
                                        </div>
                                
                                
                                </div>                             
                                </div>                                        
                                </div>                              
                                </div>
                                
                               
                            </td>
                            
                            <td>
                                <button class="btn btn-danger" data-toggle="modal" data-target="#delete${user.id}">Delete</button>
                                
                                <div class="modal fade" id="delete${user.id}" tabindex="-1"  aria-labelledby="deleteDefaultModalLabel" aria-hidden="true">
    
                                    <div class="modal-dialog">
                                        <div class="modal-content">

                                            <div class="modal-header">
                                                <h4 class="modal-title" id="deleteeModalLabel">Delete user</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                            
                                            <div class="row justify-content-center align-items-center h-100">
                                                                <div class="form-group text-center font-weight-bold">
                                                                    <label for="name">ID: </label>
                                                                    <input type="text" class="form-control" value="${user.id}" id="id" readonly/>
                                                                    <br/>
                                                                    <label for="name">Name: </label>
                                                                    <input type="text" class="form-control" value="${user.name}" id="name" readonly/>
                                                                    <br/>
                                                                    <label for="name">Surname: </label>
                                                                    <input type="text" class="form-control" value="${user.surname}" id="surname" readonly/>
                                                                    <br/>
                                                                    <label for="name">Email: </label>
                                                                    <input type="email" class="form-control" value="${user.email}" id="email" readonly/>
                                                                    <br/>
                                                                    <label for="name">Age: </label>
                                                                    <input type="text" class="form-control" value="${user.age}" id="age" readonly/>
                                                                    <br/>

                                                                    <label for="edit-roles${user.id}">Role:</label>
                                                                    <select class="custom-select " size="2" multiple type="checkbox" id="edit-roles${user.id}" readonly>
                                                
                                         
                                                                         <option> ${user.roles[0].name}</option>
                                                                         <option >
                                                 
                                                                            ${user.roles.length > 1 ? user.roles[1].name :
                                                                            " "}
                                                 
                                                                        </option>  
                                                 
                                                                        </select>

                                                                        <br/>
                                                                        <br/>

                                                                </div>
                                                            </div>
                                            
                                            <div class="modal-footer" >
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        
                                            <input class="btn btn-danger" type="submit" onclick="userDelete(${user.id}), location.href = 'http://localhost:8099/admin'" value="Delete"/>                                           
                                            </div>
                                            
                                            </div>

                                            

                                        </div>
                                    </div>
                                </div>
                                
                            </td>
                            
                        </tr>
                )`;

                table.append(allUsers);
            })
        })
}




// Создание нового пользователя
 function create() {

     let firstName = document.getElementById('AddUserName').value
     let lastName = document.getElementById('AddUserSurname').value
     let age = document.getElementById('AddUserAge').value
     let email = document.getElementById('AddUserEmail').value
     let password = document.getElementById('AddUserPassword').value

     let role = document.getElementById('role')
     let roles = []
     for (let option of role.options) {
         if (option.selected) {
             roles.push(option)
         }
     }

     let user

     if (roles.length > 1) {
         user = {
             name: firstName,
             surname: lastName,
             age: age,
             email: email,
             password: password,
             roles: [
                 {
                     id: parseInt(roles[0].value),
                     name: 'ROLE_' + roles[0].text
                 },
                 {
                     id: parseInt(roles[1].value),
                     name: 'ROLE_' + roles[1].text
                 }
             ]
         }
     } else {
             user = {
                 name: firstName,
                 surname: lastName,
                 age: age,
                 email: email,
                 password: password,
                 roles: [
                     {
                         id: parseInt(roles[0].value),
                         name: 'ROLE_' + roles[0].text
                     }
                 ]
             }
         }

        fetch('http://localhost:8099/rest/user', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify(user)
        })
            .then(response => response.json)
            .then(json => {

                getTableWithUsers()
                    document.getElementById('AddUserName').value = ''
                    document.getElementById('AddUserSurname').value = ''
                    document.getElementById('AddUserAge').value = ''
                    document.getElementById('AddUserEmail').value = ''
                    document.getElementById('AddUserPassword').value = ''
                    document.getElementById('role').value = ''

            })

}



// редактирование пользователя
function edit(id) {
    let firstName = document.getElementById('edit-name' + id).value
    let lastName = document.getElementById('edit-surname' + id).value
    let age = document.getElementById('edit-age' + id).value
    let email = document.getElementById('edit-email' + id).value

    let password = document.getElementById('edit-password' + id).value
    if (password === '') {
        alert('please, enter password for admin_edit')
        return false
    }

    let select = document.getElementById('edit-roles' + id)
    let roles = []
    for (let option of select.options) {
        if (option.selected) {
            roles.push(option)
        }
    }

    let user
    if (roles.length > 1) {
        user = {
            id: id,
            name: firstName,
            surname: lastName,
            age: age,
            email: email,
            password: password,
            roles: [
                {
                    id: parseInt(roles[0].value),
                    name: roles[0].text
                },
                {
                    id: parseInt(roles[1].value),
                    name: roles[1].text
                }
            ]
        }
    } else {
        user = {
            id: id,
            name: firstName,
            surname: lastName,
            age: age,
            email: email,
            password: password,
            roles: [
                {
                    id: parseInt(roles[0].value),
                    name: roles[0].text
                }
            ]
        }
    }
    fetch('http://localhost:8099/rest/user', {
        method: 'PUT',
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(json => {
            console.log(json)
            getTableWithUsers()
            document.querySelector('.modal-backdrop').remove()
        })
}



//удаление пользователя
function userDelete(id) {
    fetch('http://localhost:8099/rest/user/' + id, {
        method: 'DELETE'
    })
        .then(response => response.json())
        .then(json => {
            console.log(json)
            getTableWithUsers()
            document.querySelector('.modal-backdrop').remove()
        })
}







