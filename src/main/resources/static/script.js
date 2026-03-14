$(document).ready(function(){

loadData()

})

function loadData(){

$.get("/ktp",function(data){

let html=""

data.forEach(function(item){

html+=`
<tr>

<td>${item.id}</td>
<td>${item.nomorKtp}</td>
<td>${item.namaLengkap}</td>
<td>${item.alamat}</td>
<td>${item.tanggalLahir}</td>
<td>${item.jenisKelamin}</td>

<td>

<button class="btn-edit" onclick="editData(${item.id})">Edit</button>

<button class="btn-delete" onclick="hapusData(${item.id})">Hapus</button>

</td>

</tr>
`

})

$("#dataKtp").html(html)

})

}

function simpanData(){

let id=$("#id").val()

let data={

nomorKtp:$("#nomorKtp").val(),
namaLengkap:$("#namaLengkap").val(),
alamat:$("#alamat").val(),
tanggalLahir:$("#tanggalLahir").val(),
jenisKelamin:$("#jenisKelamin").val()

}

if(id==""){

$.ajax({

url:"/ktp",
type:"POST",
contentType:"application/json",
data:JSON.stringify(data),

success:function(){

alert("Data berhasil ditambahkan")

resetForm()

loadData()

},

error:function(err){

alert(err.responseText)

}

})

}else{

$.ajax({

url:"/ktp/"+id,
type:"PUT",
contentType:"application/json",
data:JSON.stringify(data),

success:function(){

alert("Data berhasil diupdate")

resetForm()

loadData()

}

})

}

}

function editData(id){

$.get("/ktp/"+id,function(data){

$("#id").val(data.id)
$("#nomorKtp").val(data.nomorKtp)
$("#namaLengkap").val(data.namaLengkap)
$("#alamat").val(data.alamat)
$("#tanggalLahir").val(data.tanggalLahir)
$("#jenisKelamin").val(data.jenisKelamin)

})

}

function hapusData(id){

if(confirm("Yakin ingin menghapus data?")){

$.ajax({

url:"/ktp/"+id,
type:"DELETE",

success:function(){

alert("Data berhasil dihapus")

loadData()

}

})

}

}

function resetForm(){

$("#id").val("")
$("#nomorKtp").val("")
$("#namaLengkap").val("")
$("#alamat").val("")
$("#tanggalLahir").val("")
$("#jenisKelamin").val("Laki-laki")

}
