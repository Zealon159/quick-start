验证是否为整数
function validInteger(value){
    return /^\d+(\.\d+)?$/i.test(value);
}