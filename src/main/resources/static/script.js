const API = "http://localhost:8080/ktp";

$(document).ready(function(){

    loadData();

});

function loadData(){

    $.ajax({
        url: API,
        type: "GET",

        success: function(response){

            let rows = "";

            response.data.forEach(function(item){

                rows += `
                <tr>
                    <td>${item.id}</td>
                    <td>${item.nomorKtp}</td>
                    <td>${item.namaLengkap}</td>
                    <td>${item.alamat}</td>
                    <td>${item.tanggalLahir}</td>
                    <td>${item.jenisKelamin}</td>
                    <td>
                        <button onclick="editData(${item.id})">Edit</button>
                        <button onclick="deleteData(${item.id})">Delete</button>
                    </td>
                </tr>
                `;

            });

            $("#tableData").html(rows);

        },

        error:function(){
            alert("Gagal mengambil data");
        }

    });

}

function saveData(){

    let id = $("#id").val();

    let data = {
        nomorKtp: $("#nomorKtp").val(),
        namaLengkap: $("#namaLengkap").val(),
        alamat: $("#alamat").val(),
        tanggalLahir: $("#tanggalLahir").val(),
        jenisKelamin: $("#jenisKelamin").val()
    };

    if(id == ""){

        $.ajax({

            url: API,
            type: "POST",
            contentType:"application/json",
            data: JSON.stringify(data),

            success:function(response){

                alert(response.message);

                clearForm();

                loadData();

            },

            error:function(){
                alert("Gagal menambah data");
            }

        });

    }else{

        $.ajax({

            url: API + "/" + id,
            type:"PUT",
            contentType:"application/json",
            data: JSON.stringify(data),

            success:function(response){

                alert(response.message);

                clearForm();

                loadData();

            },

            error:function(){
                alert("Gagal update data");
            }

        });

    }

}

function editData(id){

    $.ajax({

        url: API + "/" + id,
        type:"GET",

        success:function(response){

            let data = response.data;

            $("#id").val(data.id);
            $("#nomorKtp").val(data.nomorKtp);
            $("#namaLengkap").val(data.namaLengkap);
            $("#alamat").val(data.alamat);
            $("#tanggalLahir").val(data.tanggalLahir);
            $("#jenisKelamin").val(data.jenisKelamin);

        }

    });

}

function deleteData(id){

    if(confirm("Yakin ingin menghapus data?")){

        $.ajax({

            url: API + "/" + id,
            type:"DELETE",

            success:function(response){

                alert(response.message);

                loadData();

            },

            error:function(){
                alert("Gagal menghapus data");
            }

        });

    }

}

function clearForm(){

    $("#id").val("");
    $("#nomorKtp").val("");
    $("#namaLengkap").val("");
    $("#alamat").val("");
    $("#tanggalLahir").val("");
    $("#jenisKelamin").val("Laki-laki");

}
