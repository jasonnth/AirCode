__d(function(e,t,n,r){function a(e){return y.member(e).then(function(e){return Promise.all([l(e.help_thread),u(e.help_thread)])}).then(function(e){return{helpThread:e[0].helpThread,issueAttachments:e[0].issueAttachments,reservation:e[1]}})}function u(e){var t={_format:"for_mobile_guest"},n=e.context_value;return g.member(n,t).then(function(e){return e.reservation})}function o(e,t,n,r){var a={max_major_version:_.MAX_MAJOR_VERSION,action:R,input_values:r,node_id:t,option_value:n,return_updates_only:!0};return y.update(e,null,a).then(function(e){return l(e.help_thread)})}function i(e){return e.nodes?e.nodes.filter(function(e){return(0,f.isAttachmentLibraryNode)(e)}):null}function s(e){return A.collection({attachable_type:b,attachable_id:e}).then(function(e){return e.attachments.map(function(e){return{id:e.id,type:e.type,imageUrl:e.url,thumbnailUrl:e.thumb_url}})})}function c(e){return i(e)?s(e.id):[]}function l(e){return Promise.all(e.issues.map(function(e){return c(e).then(function(t){return{issueId:e.id,attachments:t}})})).then(function(t){var n={};return t.forEach(function(e){var t=e.issueId,r=e.attachments;n[t]=r}),{helpThread:e,issueAttachments:n}})}function h(e,t){return new Promise(function(n,r){var a=new XMLHttpRequest;a.onload=function(){if(a.status<400){var e=JSON.parse(a.responseText),t=e.attachment,u=t.id,o=t.url,i=t.thumb_url,s=t.type;n({id:u,type:s,imageUrl:o,thumbnailUrl:i})}else r(new Error(a.responseText))},a.onerror=function(e){r(e)};var u=A.getBaseUrl()+"/"+v;a.open("POST",u);var o=new FormData,i={attachable_type:b,attachable_id:e};o.append("json_root_body",JSON.stringify(i)),o.append("data",{uri:t,type:"image/jpeg",name:"image.jpg"}),a.send(o)})}function d(e){return A.delete(e)}Object.defineProperty(r,"__esModule",{value:!0}),r.ReservationResource=r.HelpThreadAttachmentsResource=r.HelpThreadResource=void 0,r.getHelpThread=a,r.fetchReservation=u,r.postSelectedHelpOption=o,r.fetchAttachmentsForIssue=s,r.getAttachments=c,r.uploadAttachment=h,r.deleteAttachment=d;var p=t(727),m=babelHelpers.interopRequireDefault(p),f=t(2263),_=t(2264),v="attachments",b="help_threads",R="option_selected",y=r.HelpThreadResource=new m.default(b),A=r.HelpThreadAttachmentsResource=new m.default(v),g=r.ReservationResource=new m.default("reservations")},2262);