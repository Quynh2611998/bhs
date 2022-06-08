export function Validator (options) {
    // 
    function getParentElement(element, selector) {
        while(element.parentElement) {
            if(element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }

    var rulesSelctor = {};
 // thực hiện validate
 function Validate (inputElement, rule) {
    var errorMessage
    var errorElement = getParentElement(inputElement,options.formGroupSelector).querySelector(options.errorSelector);
    var rules = rulesSelctor[rule.selector];
   
    for(var i = 0; i < rules.length; i++ ) {
        switch(inputElement.type) {
            case 'radio':
            case 'checkbox':
                errorMessage = rules[i](
                    formElement.querySelector(rule.selector +':checked')
                );
                break;
            default:
                errorMessage = rules[i](inputElement.value);

        }
        
         if(errorMessage) break;
    }
 
  if(errorMessage) {
     errorElement.innerText = errorMessage;
      getParentElement(inputElement,options.formGroupSelector).classList.add('invalid');
  }else {
     errorElement.innerText = '';
      getParentElement(inputElement,options.formGroupSelector).classList.remove('invalid');
  }
  return !errorMessage;
 }
 // lấy element của form cần validate
 var formElement = document.querySelector(options.form);
 if(formElement) {
     formElement.onsubmit = function(e) {
       e.preventDefault();
         var isFormValid = true;      
         // lặp qua từng rule và validate
         options.rules.forEach(function(rule) {
            var inputElement = formElement.querySelector(rule.selector);
           var isValid = Validate(inputElement,rule)
           if(!isValid) {
               isFormValid = false;
           }
         });
         if(isFormValid){
             // trường hợp submit với javascript
            if(typeof options.onSubmit === 'function') {
                var enableInputs = formElement.querySelectorAll('[name]:not([disable])');              
                var formValues =  Array.from(enableInputs) .reduce(function(values, input){
                    switch(input.type){
                        case 'radio':
                            values[input.name] = formElement.querySelector('input[name ="' + input.name +'"]:checked').value;     
                            break;
                        case 'checkbox':
                            if(!input.matches(':checked')) return values;
                            if(!Array.isArray(values[input.name])) {
                               values[input.name] = [];
                            }
                            values[input.name].push(input.value);
                            break;
                        case 'file' :
                            values[input.name] = input.files;    
                            break;
                        default:
                            values[input.name] = input.value    
                    }
                   
                    return values;
                },{});            
               options.onSubmit(formValues);
            }
         }
         // trường hợp submit mặc đình trình duyệt
        //   else{
        //      formElement.submit();
        //   }
      
     }
     // lặp qua mỗi rule mà sử lý sự kiện
     options.rules.forEach(function(rule) {
        // lưu lại các rule của mua input
         if(Array.isArray( rulesSelctor[rule.selector])){
            rulesSelctor[rule.selector].push(rule.test);

         }else {
            rulesSelctor[rule.selector] = [rule.test];
         }
        var inputElements = formElement.querySelectorAll(rule.selector);
         Array.from(inputElements).forEach(function(inputElement) {
           
                inputElement.onblur = function() {
                 Validate(inputElement,rule)
                    
                }
                inputElement.oninput = function() {
                 var errorElement =  getParentElement(inputElement,options.formGroupSelector).querySelector(options.errorSelector);
                 errorElement.innerText = '';
                  getParentElement(inputElement,options.formGroupSelector).classList.remove('invalid');
                }
             
         });
         
        
     });

 }
 
}

Validator.isRequired = function(selector) {
 return {
     selector: selector,
     test: function(value) {
        return value ? undefined : 'Vui lòng nhập trường này'
     }
 }
}

Validator.isEmail = function(selector) {
return {
     selector: selector,
     test: function(value) {
         var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
         return regex.test(value) ? undefined : 'Vui lòng nhập Email'
     }
 }
}
Validator.minLength = function(selector, min) { 
    return {
         selector: selector,
         test: function(value) {
             return value.length >= 6 ? undefined :`Mật khẩu cần ít nhất ${min} kí tự`
         }
     }
    }

Validator.isConfirmed = function(selector, getConfirmValue) { 
   
    return {
        selector: selector,
        test: function(value) {
            return value === getConfirmValue() ? undefined :`Mật khẩu không khớp`
        }
    }
    }    

    Validator.isPhoneNumber = function(selector) { 
   
        return {
            selector: selector,
            test: function(value) {
                var regex = /((09|03|07|08|05)+([0-9]{8})\b)/g;
                return regex.test(value)? undefined :`Số điện thoại không hợp lệ`
            }
        }
        }        