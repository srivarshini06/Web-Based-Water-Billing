const API='http://localhost:8080';
function token(){return localStorage.getItem('token')||'';}
async function login(){const res=await fetch(API+'/api/users/login',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({email:email.value,password:password.value})});const data=await res.json();if(data.token)localStorage.setItem('token',data.token);out.textContent=JSON.stringify(data,null,2);}
async function uploadCSV(){const f=file.files[0];if(!f){out.textContent='Choose a CSV file';return;}const fd=new FormData();fd.append('file',f);const res=await fetch(API+'/api/readings/upload-csv',{method:'POST',headers:{Authorization:'Bearer '+token()},body:fd});out.textContent=JSON.stringify(await res.json(),null,2);}
async function loadInvoices(){const res=await fetch(API+'/api/invoices/resident/'+residentId.value,{headers:{Authorization:'Bearer '+token()}});out.textContent=JSON.stringify(await res.json(),null,2);}
