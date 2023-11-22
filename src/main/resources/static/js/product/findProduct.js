function findProduct(){
    console.log('findProduct()');

    let findProductName = $("#registProductForm input[type='text']").val();
    let zip_code = $('#wrap section #sectionWrap #registProduct input[name="zip_code"]').val();

    if(findProductName == ''){
        alert('Input Please Product');
        findProductName.focus();
    }
    else {
        ajax_addProduct(zip_code, findProductName);
    }
}

function allfindProduct(){
    console.log('allfindProduct()');

    let zip_code = $('#wrap section #sectionWrap #registProduct input[name="zip_code"]').val();

    let findProductName = "";
    ajax_addProduct(zip_code, findProductName);

    $("#registProductForm input[type='text']").val("");
}

function ajax_addProduct(zipCode, name){
    console.log('ajax_addProduct()');

    let msgDto = {
        name : name,
        zipCode : zipCode
    }

    $.ajax({
        url: '/product/findProduct',
        method: 'POST',
        data: JSON.stringify(msgDto),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data) {
            console.log('AJAX SUCCESS - ajax_addProduct()');

            if(data.mySchoolProductDtos.length == 0)
                alert('상품을 찾을 수 없습니다.');

            else{
                $("#selectProduct table").children().remove();

                for (let i = 0; i < data.mySchoolProductDtos.length; i += 5) {
                    let appendTag = "<tr>";

                    for (let j = i; j < i + 5 && j < data.mySchoolProductDtos.length; j++) {
                        appendTag += "<td>";
                        appendTag += "<a href='#none' class='add_product' data-product-name='" + data.mySchoolProductDtos[j].product_name;
                        appendTag += "' data-product-price='" + data.mySchoolProductDtos[j].product_price;
                        if(data.mySchoolProductDtos[j].img == ''){
                            appendTag += "' data-img='default/default.jpg'>";
                            appendTag += '<img width="100" height="100" src="/dormEaseUploadImg/admin/product/default/default.jpg">';
                        }
                        else {
                            appendTag += "' data-img='" + data.mySchoolProductDtos[j].img + "'>";
                            appendTag += '<img width="100" height="100" src="/dormEaseUploadImg/admin/product/' + data.mySchoolProductDtos[j].img + '">';
                        }
                        appendTag += '<span class="name">' + data.mySchoolProductDtos[j].product_name + '</span>'
                        appendTag += '<span class="price">' + data.mySchoolProductDtos[j].product_price + '</span>'
                        appendTag += "</a>";
                        appendTag += "</td>";
                    }

                    appendTag += "</tr>";
                    $("#selectProduct table").append(appendTag);
                }
                if(name != ''){     //전체 보기 일땐 alert (x)
                    alert('상품 검색이 완료되었습니다.');
                }
            }

        },
        error: function(data) {
            console.log('AJAX ERROR - ajax_addProduct()');

            alert('상품을 찾을 수 없습니다.');
        }
    });
}