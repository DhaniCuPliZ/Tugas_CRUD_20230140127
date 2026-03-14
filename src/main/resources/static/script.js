const API_URL = "/ktp";

$(document).ready(function(){

loadData();

$("#ktpForm").submit(function(e){

e.preventDefault();

const id = $("#id").val();

const data = {
nomorKtp: $("#nomorKtp").val(),
namaLengkap: $("#namaLengkap").val(),
alamat: $("#alamat").val(),
tanggalLahir: $("#tanggalLahir").val(),
jenisKelamin: $("#jenisKelamin").val()
};

if(id){
updateData(id,data);
}else{
createData(data);
}

});

});



function loadData(){

$.ajax({
url:API_URL,
type:"GET",

success:function(res){

let rows="";

res.data.forEach(item=>{

let tanggal = item.tanggalLahir.split("T")[0];

rows+=`
<tr>
<td>${item.id}</td>
<td>${item.nomorKtp}</td>
<td>${item.namaLengkap}</td>
<td>${item.alamat}</td>
<td>${tanggal}</td>
<td>${item.jenisKelamin}</td>
<td>
<button class="btn-edit" onclick="editData(${item.id})">Edit</button>
<button class="btn-delete" onclick="deleteData(${item.id})">Delete</button>
</td>
</tr>
`;

});

$("#dataTable").html(rows);

}

});

}



function createData(data){

$.ajax({
url:API_URL,
type:"POST",
contentType:"application/json",
data:JSON.stringify(data),

success:function(res){

alert(res.message);

resetForm();

loadData();

}

});

}



function editData(id){

$.ajax({
url:API_URL+"/"+id,
type:"GET",

success:function(res){

const d = res.data;

$("#id").val(d.id);
$("#nomorKtp").val(d.nomorKtp);
$("#namaLengkap").val(d.namaLengkap);
$("#alamat").val(d.alamat);

let tanggal = d.tanggalLahir.split("T")[0];
$("#tanggalLahir").val(tanggal);

$("#jenisKelamin").val(d.jenisKelamin);

}

});

}



function updateData(id,data){

$.ajax({
url:API_URL+"/"+id,
type:"PUT",
contentType:"application/json",
data:JSON.stringify(data),

success:function(res){

alert(res.message);

resetForm();

loadData();

}

});

}



function deleteData(id){

if(confirm("Yakin ingin menghapus data?")){

$.ajax({
url:API_URL+"/"+id,
type:"DELETE",

success:function(res){

alert(res.message);

loadData();

}

});

}

}



function resetForm(){

$("#id").val("");
$("#nomorKtp").val("");
$("#namaLengkap").val("");
$("#alamat").val("");
$("#tanggalLahir").val("");
$("#jenisKelamin").val("Laki-laki");

}
