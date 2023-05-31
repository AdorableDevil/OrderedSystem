async function loginApi(data) {
  return $axios({
    'url': '/employee/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/employee/logout',
    'method': 'post',
  })
}


function check(data) {
  return $axios({
    'url': `/captcha/check?captcha=${data}`,
    // 'url': '/captcha/check',
    'method': 'get',
    data
  })
}