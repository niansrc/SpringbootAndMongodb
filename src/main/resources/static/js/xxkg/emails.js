/**
 * Created by niannian on 2016/12/29.
 */
$(document).ready(function(){
    $.ajax({
        type:'post',
        url:'/goods',
        success:function (msg) {
            // console.log(msg);
            var len=msg.length;
            //搜索物品
            $('#xxkg .right .write .row .search input').keyup(function(){
                $('#xxkg .right .write .row .xxkg-result ul').html("");
                var input=$(this).val();
                for(var i=0;i<len;i++){
                    var aim=msg[i].good;
                    var ke=msg[i].key;
                    ke=ke.toString();
                    if((aim.indexOf(input)>=0)||ke.indexOf(input)>=0){
                        // console.log(aim);
                        var li=$('<li class="list-group-item" alt="'+ke+'">'+aim+'</li>');
                        $('#xxkg .right .write .row .xxkg-result ul').append(li);
                        (function (ai,k) {
                            li.click(function(){
                                var len=$('#xxkg .right .write .col-xs-8 .awards .form-control span.good-name').length;
                                if(len==3){
                                    alert('奖励不能超过3种!');
                                }else{
                                    var award=$('<span class="item"><span class="good-name" alt="'+k+'">'+ai+'</span><span class="good-number" contenteditable="true">12</span></span>');
                                    $('#xxkg .right .write .col-xs-8 .awards .form-control').append(award);
                                    // var arr=[];
                                    // var awards='';
                                    // $('#xxkg .right .write .col-xs-8 .awards .form-control span.item span').each(function(index,elem){
                                    //     if($(this).attr("alt")){
                                    //         arr.push($(this).attr("alt"));
                                    //     }else{
                                    //         arr.push($(this).text());
                                    //     }
                                    //     awards=arr.join(",");
                                    // })
                                    // $('#xxkg .right .write .col-xs-8 .awards input.awards').val(awards);
                                    $('#xxkg .right .write .col-xs-8 .awards .form-control span.good-name').each(function(index,elem){
                                        $(this).click(function(){
                                            $(this).parent(".item").remove();
                                        })
                                    })
                                }
                            })
                        })(aim,ke);
                    }
                }
            })
        }
    })
    $('#xxkg .right .write .row .xxkg-result ul li').each(function(index,elem) {
        $(this).click(function(){
            var len=$('#xxkg .right .write .col-xs-8 .awards .form-control span.good-name').length;
            if(len==3){
                alert('奖励不能超过3种!');
            }else{
                var name=$(elem).text();
                var key=$(elem).attr("alt");
                var award=$('<span class="item"><span class="good-name" alt="'+key+'">'+name+'</span><span class="good-number" contenteditable="true">12</span></span>');
                $('#xxkg .right .write .col-xs-8 .awards .form-control').append(award);
                $('#xxkg .right .write .col-xs-8 .awards .form-control span.good-name').each(function(index,elem){
                    $(this).click(function(){
                        $(this).parent(".item").remove();
                    })
                })
            }
        });
    })
    $('#xxkg .right .write .col-xs-8 button.send').click(function(){
        var title=$('#xxkg .right .write .col-xs-8 input[name="title"]').val();
        var content=$('#xxkg .right .write .col-xs-8 textarea[name="content"]').val();
        var create=$('#xxkg .right .write .col-xs-8 input[name="create"]').val();
        // create=create+" 00:00:00";
        var end=$('#xxkg .right .write .col-xs-8 input[name="end"]').val();
        // end=end+" 00:00:00";
        var level=$('#xxkg .right .write .col-xs-8 input[name="level"]').val();
        var vip=$('#xxkg .right .write .col-xs-8 input[name="vip"]').val();
        var arr=[];
        var awards='';
        $('#xxkg .right .write .col-xs-8 .awards .form-control span.item span').each(function(index,elem){
            if($(this).attr("alt")){
                arr.push($(this).attr("alt"));
            }else{
                arr.push($(this).text());
            }
            awards=arr.join(",");
        })
        // console.log(title,content,awards,create,end,level,vip);
        console.log("create:"+create);
        console.log("end:"+end);
        $.ajax({
            type:'post',
            url:'/emails',
            data:{
                title:title,
                content:content,
                awards:awards,
                create:create,
                end:end,
                level:level,
                vip:vip
            },
            success:function(msg){
                console.log(msg);
                $('#a_success').fadeIn(300,function(){
                    $('#a_success').fadeOut(600);
                });
            },
            error:function(msg){
                $('#a_fail').fadeIn(300,function(){
                    $('#a_fail').fadeOut(600);
                });
            }
        })

    })
})