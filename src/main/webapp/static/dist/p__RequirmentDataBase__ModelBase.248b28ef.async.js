(self.webpackChunkant_design_pro=self.webpackChunkant_design_pro||[]).push([[421],{42880:function(ee,w,e){"use strict";e.r(w),e.d(w,{default:function(){return ye}});var P=e(12968),N=e(62462),o=e(48736),j=e(27049),p=e(2824),h=e(57895),$=e(75362),O=e(57663),A=e(71577),g=e(11849),C=e(34792),v=e(48086),b=e(3182),m=e(62350),f=e(75443),_=e(94043),a=e.n(_),n=e(49101),y=e(19595),r=e(67294),U=e(53858),X=e(43185),ue=e(94412),te=e(86582),je=e(9715),se=e(86585),le=e(7085),ie=e(84391),oe=e(37476),de=e(5966),ce=e(90672),t=e(85893),me=function(E){var J=se.Z.useForm(),L=(0,p.Z)(J,1),B=L[0],K=(0,r.useState)([]),Y=(0,p.Z)(K,2),S=Y[0],F=Y[1],R=(0,r.useState)(void 0),x=(0,p.Z)(R,2),W=x[0],k=x[1],z=(0,r.useState)([]),Z=(0,p.Z)(z,2),V=Z[0],I=Z[1],G=(0,t.jsxs)("div",{children:[W?(0,t.jsx)(le.Z,{}):(0,t.jsx)(n.Z,{}),(0,t.jsx)("div",{style:{marginTop:8},children:"Upload"})]}),q=function(d,M){var u=new FileReader;u.addEventListener("load",function(){return M(u.result)}),u.readAsDataURL(d)};return(0,t.jsxs)(oe.Z,{form:B,title:"\u7EC4\u4EF6\u7A97\u53E3",width:"800px",layout:"horizontal",labelCol:{span:4},wrapperCol:{span:14},modalProps:{onCancel:function(){var D=(0,b.Z)(a().mark(function M(){return a().wrap(function(s){for(;;)switch(s.prev=s.next){case 0:case"end":return s.stop()}},M)}));function d(){return D.apply(this,arguments)}return d}()},visible:E.modalVisible,onVisibleChange:function(){var D=(0,b.Z)(a().mark(function d(M){var u,s,c,i,T,l;return a().wrap(function(Q){for(;;)switch(Q.prev=Q.next){case 0:if(!M){Q.next=17;break}if(B.resetFields(),S.length=0,k(""),E.currentRecord==null){Q.next=17;break}return Q.next=7,(0,U.PD)({id:(u=E.currentRecord)===null||u===void 0?void 0:u.id});case 7:for(s=Q.sent,B.setFieldsValue(s),s.picPath!=null&&k("/algz/common/file/down?pathcode="+s.picPath),c=s.filePath==null?[]:s.filePath.split(","),i=s.fileName==null?[]:s.fileName.split(","),T=[],l=0;l<c.length;l++)T=[].concat((0,te.Z)(T),[{uid:c[l],name:i[l],status:"done",url:"/algz/common/file/down?pathcode="+c[l]}]);F(T),T=[{uid:s.picPath,name:s.picName,status:"done",url:"/algz/common/file/down?pathcode="+s.picPath}],I(T);case 17:E.onVisibleChange(M);case 18:case"end":return Q.stop()}},d)}));return function(d){return D.apply(this,arguments)}}(),onFinish:function(){var D=(0,b.Z)(a().mark(function d(M){var u,s,c,i;return a().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return s=new FormData,S.forEach(function(ae){s.append("file",ae)}),V.forEach(function(ae){s.append("picFile",ae)}),E.currentRecord!=null&&s.append("id",E.currentRecord.id+""),s.append("kindId",E.selectNodeKey.kindId),s.append("name",B.getFieldValue("name")),s.append("description",(u=B.getFieldValue("description"))!==null&&u!==void 0?u:""),c=v.default.loading("\u6B63\u5728\u63D0\u4EA4"),l.prev=8,l.next=11,(0,U.FJ)(s);case 11:if(i=l.sent,i!=""){l.next=19;break}c(),v.default.success("\u63D0\u4EA4\u6210\u529F\uFF01"),E.onVisibleChange(!1),E.updateParentTable(),l.next=20;break;case 19:throw new Error(i);case 20:l.next=26;break;case 22:l.prev=22,l.t0=l.catch(8),c(),v.default.error(l.t0.message);case 26:case"end":return l.stop()}},d,null,[[8,22]])}));return function(d){return D.apply(this,arguments)}}(),initialValues:{},children:[(0,t.jsx)(de.Z,{name:"name",label:"\u7EC4\u4EF6\u540D\u79F0",width:"md",tooltip:"\u6700\u957F\u4E3A 24 \u4F4D",placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0",rules:[{required:!0}]}),(0,t.jsx)(se.Z.Item,{label:"\u7F29\u7565\u56FE",children:(0,t.jsx)(ue.Z,{listType:"picture-card",className:"avatar-uploader",showUploadList:!1,action:"/algz/common/file/upload",beforeUpload:function(d){var M=d.type==="image/jpeg"||d.type==="image/png";M||v.default.error("\u4F60\u53EA\u80FD\u4E0A\u4F20 JPG/PNG \u6587\u4EF6!");var u=d.size/1024/1024<2;return u||v.default.error("\u56FE\u7247\u5FC5\u987B\u5C0F\u4E8E 2MB!"),I([d]),!1},onChange:function(d){q(d.file,function(M){k(M)})},children:W?(0,t.jsx)("img",{src:W,alt:"avatar",style:{width:"100%"}}):G})}),(0,t.jsx)(se.Z.Item,{label:"\u6A21\u578B\u5730\u5740",children:(0,t.jsx)(ue.Z,{name:"files",action:"/algz/common/file/upload",fileList:S,onRemove:function(){var D=(0,b.Z)(a().mark(function d(M){var u,s;return a().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:return i.next=2,(0,U.KI)({id:(u=E.currentRecord)===null||u===void 0?void 0:u.id,filePath:M.uid});case 2:s=S.indexOf(M),S.splice(s,1),F((0,te.Z)(S));case 5:case"end":return i.stop()}},d)}));return function(d){return D.apply(this,arguments)}}(),beforeUpload:function(d){return F([].concat((0,te.Z)(S),[d])),console.log("Upload event:",F),!1},children:(0,t.jsx)(A.Z,{icon:(0,t.jsx)(ie.Z,{}),children:"\u5355\u51FB\u4E0A\u4F20"})})}),(0,t.jsx)(ce.Z,{name:"description",label:"\u8BF4\u660E",tooltip:"\u6700\u957F\u4E3A 225 \u4F4D",placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0"})]})},fe=me,pe=function(E){var J=function(Z,V,I){return(0,t.jsx)(f.Z,{title:"\u662F\u5426".concat(Z,"\u8FD9\u4E2A\u9879\u76EE\uFF1F"),onConfirm:function(){return I(V)},okText:"\u662F",cancelText:"\u5426",children:(0,t.jsx)("a",{children:Z})},"popconfirm1")},L=(0,r.useRef)(),B=(0,r.useState)(!1),K=(0,p.Z)(B,2),Y=K[0],S=K[1],F=(0,r.useState)(),R=(0,p.Z)(F,2),x=R[0],W=R[1],k=[{dataIndex:"id",valueType:"indexBorder",width:48},{title:"\u540D\u79F0",dataIndex:"name"},{title:"\u8BF4\u660E",dataIndex:"description"},{title:"\u64CD\u4F5C",dataIndex:"option",valueType:"option",width:100,render:function(Z,V){return[(0,t.jsx)("a",{onClick:function(){S(!0),W(V)},children:"\u7F16\u8F91"},"editor"),J("\u5220\u9664",V,function(){var I=(0,b.Z)(a().mark(function G(q){var D,d;return a().wrap(function(u){for(;;)switch(u.prev=u.next){case 0:return D=v.default.loading("\u6B63\u5728\u5220\u9664"),u.prev=1,u.next=4,(0,U.Fv)((0,g.Z)({},q));case 4:return D(),v.default.success("\u5220\u9664\u6210\u529F\uFF01"),(d=L.current)===null||d===void 0||d.reload(),u.abrupt("return",!0);case 10:return u.prev=10,u.t0=u.catch(1),D(),v.default.error("\u5220\u9664\u5931\u8D25,\u8BF7\u518D\u8BD5\u4E00\u6B21\uFF01"),u.abrupt("return",!1);case 15:case"end":return u.stop()}},G,null,[[1,10]])}));return function(G){return I.apply(this,arguments)}}())]}}];return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(y.ZP,{actionRef:L,headerTitle:"\u7EC4\u4EF6\u5217\u8868",rowKey:"id",search:!1,manualRequest:!0,request:U.hM,params:E.requestParams,columns:k,onRow:function(Z){return{onClick:function(){E.onClick(Z),W(Z)}}},tableAlertRender:!1,rowSelection:{type:"radio",hideSelectAll:!0,selectedRowKeys:[(x==null?void 0:x.id)+""]},toolBarRender:function(){return[(0,t.jsxs)(A.Z,{type:"primary",onClick:function(){S(!0),W(null)},children:[(0,t.jsx)(n.Z,{})," \u65B0\u5EFA"]},"primary")]}}),(0,t.jsx)(fe,{selectNodeKey:E.requestParams,modalVisible:Y,onVisibleChange:S,currentRecord:x,updateParentTable:function(){var Z;(Z=L.current)===null||Z===void 0||Z.reload()}})]})},_e=pe,De=e(59250),ve=e(13013),Me=e(32157),he=e(87524),Ce=e(30887),ne=e(99210),ge=e(13983),Ee=e(50144),re=e(83635),Pe=function(H,E){var J=(0,r.useState)(),L=(0,p.Z)(J,2),B=L[0],K=L[1],Y=(0,r.useState)(),S=(0,p.Z)(Y,2),F=S[0],R=S[1],x=(0,r.useState)(!0),W=(0,p.Z)(x,2),k=W[0],z=W[1],Z=(0,r.useState)(!0),V=(0,p.Z)(Z,2),I=V[0],G=V[1],q=(0,t.jsxs)(ne.Z,{onClick:function(c){switch(c.key){case"1":u(!0);break;case"2":u(!0);break;case"3":break}},children:[(0,t.jsx)(ne.Z.Item,{disabled:k,children:"\u65B0\u589E"},"1"),(0,t.jsx)(ne.Z.Item,{disabled:I,children:"\u4FEE\u6539"},"2"),(0,t.jsx)(ne.Z.Item,{disabled:I,children:(0,t.jsx)(ge.Z,{text:"\u5220\u9664",tableRef:E,onConfirm:function(){var s=(0,b.Z)(a().mark(function c(i){return a().wrap(function(l){for(;;)switch(l.prev=l.next){case 0:return l.next=2,(0,re.yL)({id:F==null?void 0:F.key});case 2:return l.t0=K,l.next=5,(0,re.pN)();case 5:l.t1=l.sent,(0,l.t0)(l.t1);case 7:case"end":return l.stop()}},c)}));return function(c){return s.apply(this,arguments)}}()},"del")},"3")]}),D=(0,r.useState)(!1),d=(0,p.Z)(D,2),M=d[0],u=d[1];return(0,r.useEffect)(function(){(0,b.Z)(a().mark(function s(){var c;return a().wrap(function(T){for(;;)switch(T.prev=T.next){case 0:return T.next=2,(0,re.pN)();case 2:c=T.sent,K(c),c.length>0&&(R(c[0]),H.onSelectedKey(c[0].key));case 5:case"end":return T.stop()}},s)}))()},[]),(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(ve.Z,{overlay:q,trigger:["contextMenu"],children:(0,t.jsx)("span",{onMouseUp:function(c){if(c.button==2){var i=c.target;i.nodeName=="DIV"?(z(!1),G(!0)):i.nodeName=="SPAN"&&(z(!0),G(!1))}},children:(0,t.jsx)(he.Z,{showLine:!0,showIcon:!1,autoExpandParent:!0,onRightClick:function(c){R(c.node)},selectedKeys:[(F==null?void 0:F.key)+""],treeData:B,onSelect:function(c,i){R(i.node),H.onSelectedKey(i.node.key)}})})}),(0,t.jsx)(Ee.Z,{visible:M,onVisibleChange:u,currentRecord:F,updateTree:(0,b.Z)(a().mark(function s(){return a().wrap(function(i){for(;;)switch(i.prev=i.next){case 0:return i.t0=K,i.next=3,(0,re.pN)();case 3:i.t1=i.sent,(0,i.t0)(i.t1);case 5:case"end":return i.stop()}},s)})),editState:!I})]})},ye=function(){var H=(0,r.useState)(void 0),E=(0,p.Z)(H,2),J=E[0],L=E[1],B=(0,r.useState)(),K=(0,p.Z)(B,2),Y=K[0],S=K[1];return(0,t.jsx)($.ZP,{children:(0,t.jsxs)(h.ZP,{split:"vertical",children:[(0,t.jsxs)(h.ZP,{colSpan:"20%",style:{overflow:"hidden"},children:[(0,t.jsx)(j.Z,{orientation:"left",children:"\u7C7B\u522B"}),(0,t.jsx)(Pe,{onSelectedKey:function(R){S(R)}})]}),(0,t.jsx)(h.ZP,{colSpan:"60%",style:{overflow:"hidden"},children:(0,t.jsx)(_e,{requestParams:{kindId:Y},onClick:function(R){L(R.picPath==null?void 0:"/algz/common/file/down?pathcode=".concat(R.picPath))}})}),(0,t.jsx)(h.ZP,{title:"\u7F29\u7565\u56FE",colSpan:"20%",headerBordered:!0,layout:"center",children:(0,t.jsx)(N.Z,{width:200,height:200,src:J})})]})})}},53858:function(ee,w,e){"use strict";e.d(w,{hM:function(){return p},FJ:function(){return $},PD:function(){return A},Fv:function(){return C},KI:function(){return b}});var P=e(3182),N=e(94043),o=e.n(N),j=e(48971);function p(f){return h.apply(this,arguments)}function h(){return h=(0,P.Z)(o().mark(function f(_){return o().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,j.WY)("/algz/modelmanager/models",{params:_}));case 1:case"end":return n.stop()}},f)})),h.apply(this,arguments)}function $(f){return O.apply(this,arguments)}function O(){return O=(0,P.Z)(o().mark(function f(_){return o().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,j.WY)("/algz/modelmanager/savemodel",{method:"POST",processData:!1,requestType:"form",data:_}));case 1:case"end":return n.stop()}},f)})),O.apply(this,arguments)}function A(f){return g.apply(this,arguments)}function g(){return g=(0,P.Z)(o().mark(function f(_){return o().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,j.WY)("/algz/modelmanager/model",{params:_}));case 1:case"end":return n.stop()}},f)})),g.apply(this,arguments)}function C(f){return v.apply(this,arguments)}function v(){return v=(0,P.Z)(o().mark(function f(_){return o().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,j.WY)("/algz/modelmanager/delmodel",{params:_}));case 1:case"end":return n.stop()}},f)})),v.apply(this,arguments)}function b(f){return m.apply(this,arguments)}function m(){return m=(0,P.Z)(o().mark(function f(_){return o().wrap(function(n){for(;;)switch(n.prev=n.next){case 0:return n.abrupt("return",(0,j.WY)("/algz/modelmanager/delmodelfile",{params:_}));case 1:case"end":return n.stop()}},f)})),m.apply(this,arguments)}},50144:function(ee,w,e){"use strict";var P=e(3182),N=e(9715),o=e(86585),j=e(2824),p=e(94043),h=e.n(p),$=e(37476),O=e(5966),A=e(83635),g=e(85893);w.Z=function(C){var v=o.Z.useForm(),b=(0,j.Z)(v,1),m=b[0];return(0,g.jsx)($.Z,{title:"\u7C7B\u522B",width:"500px",layout:"horizontal",form:m,modalProps:{onCancel:function(){var f=(0,P.Z)(h().mark(function a(){return h().wrap(function(y){for(;;)switch(y.prev=y.next){case 0:case"end":return y.stop()}},a)}));function _(){return f.apply(this,arguments)}return _}()},visible:C.visible,onVisibleChange:function(){var f=(0,P.Z)(h().mark(function _(a){var n,y;return h().wrap(function(U){for(;;)switch(U.prev=U.next){case 0:m.resetFields(),a&&C.editState&&m.setFieldsValue({id:(n=C.currentRecord)===null||n===void 0?void 0:n.key,name:(y=C.currentRecord)===null||y===void 0?void 0:y.title}),C.onVisibleChange(a);case 3:case"end":return U.stop()}},_)}));return function(_){return f.apply(this,arguments)}}(),onFinish:function(){var f=(0,P.Z)(h().mark(function _(a){var n,y,r;return h().wrap(function(X){for(;;)switch(X.prev=X.next){case 0:return y=C.editState?(n=C.currentRecord)===null||n===void 0?void 0:n.key:void 0,r=m.getFieldValue("name"),X.next=4,(0,A.wM)({id:y,name:r});case 4:C.updateTree(),C.onVisibleChange(!1);case 6:case"end":return X.stop()}},_)}));return function(_){return f.apply(this,arguments)}}(),children:(0,g.jsx)(O.Z,{name:"name",label:"\u540D\u79F0\uFF1A",width:"md",tooltip:"\u6700\u957F\u4E3A 24 \u4F4D",placeholder:"\u8BF7\u8F93\u5165\u540D\u79F0"})})}},83635:function(ee,w,e){"use strict";e.d(w,{pL:function(){return p},AQ:function(){return $},MT:function(){return A},pN:function(){return C},wM:function(){return b},yL:function(){return f}});var P=e(3182),N=e(94043),o=e.n(N),j=e(48971);function p(a){return h.apply(this,arguments)}function h(){return h=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticsmanager/semanticss",{params:n}));case 1:case"end":return r.stop()}},a)})),h.apply(this,arguments)}function $(a){return O.apply(this,arguments)}function O(){return O=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticsmanager/delsemantics",{method:"POST",requestType:"form",data:n}));case 1:case"end":return r.stop()}},a)})),O.apply(this,arguments)}function A(a){return g.apply(this,arguments)}function g(){return g=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticsmanager/savesemantics",{method:"POST",requestType:"form",data:n}));case 1:case"end":return r.stop()}},a)})),g.apply(this,arguments)}function C(a){return v.apply(this,arguments)}function v(){return v=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticskindmanager/semanticskinds",{params:n}));case 1:case"end":return r.stop()}},a)})),v.apply(this,arguments)}function b(a){return m.apply(this,arguments)}function m(){return m=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticskindmanager/savesemanticskind",{method:"POST",requestType:"form",data:n}));case 1:case"end":return r.stop()}},a)})),m.apply(this,arguments)}function f(a){return _.apply(this,arguments)}function _(){return _=(0,P.Z)(o().mark(function a(n){return o().wrap(function(r){for(;;)switch(r.prev=r.next){case 0:return r.abrupt("return",(0,j.WY)("/algz/semanticskindmanager/delsemanticskind",{method:"POST",requestType:"form",data:n}));case 1:case"end":return r.stop()}},a)})),_.apply(this,arguments)}},13983:function(ee,w,e){"use strict";var P=e(62350),N=e(75443),o=e(11849),j=e(34792),p=e(48086),h=e(3182),$=e(94043),O=e.n($),A=e(85893);w.Z=function(g){return(0,A.jsx)(N.Z,{title:"\u662F\u5426".concat(g.text,"\u8FD9\u4E2A\u9879\u76EE\uFF1F"),onConfirm:(0,h.Z)(O().mark(function C(){var v;return O().wrap(function(m){for(;;)switch(m.prev=m.next){case 0:return v=p.default.loading("\u6B63\u5728".concat(g.text)),m.prev=1,m.next=4,g.onConfirm((0,o.Z)({},g.record));case 4:return v(),p.default.success("\u6210\u529F\uFF01"),g.tableRef.current.reload(),m.abrupt("return",!0);case 10:return m.prev=10,m.t0=m.catch(1),v(),p.default.error("\u5931\u8D25,\u8BF7\u518D\u8BD5\u4E00\u6B21\uFF01"),m.abrupt("return",!1);case 15:case"end":return m.stop()}},C,null,[[1,10]])})),okText:"\u662F",cancelText:"\u5426",children:(0,A.jsx)("a",{children:g.text})})}}}]);
