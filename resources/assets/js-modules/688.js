__d(function(e,t,r,i){"use strict";function a(){return!("undefined"==typeof window||!window.document||!window.document.createElement)}function o(e,t){n.addType(e,function(r,i,a){var o,p,l,s,d=i,f=(new Date).getTime();if(!r){d={},s=[],l=0;try{for(r=t.length;r=t.key(l++);)u.test(r)&&(p=JSON.parse(t.getItem(r)),p.expires&&p.expires<=f?s.push(r):d[r.replace(u,"")]=p.data);for(;r=s.pop();)t.removeItem(r)}catch(e){}return d}if(r="__amplify__"+r,void 0===i){if(o=t.getItem(r),p=o?JSON.parse(o):{expires:-1},!(p.expires&&p.expires<=f))return p.data;t.removeItem(r)}else if(null===i)t.removeItem(r);else{p=JSON.stringify({data:i,expires:a.expires?f+a.expires:null});try{t.setItem(r,p)}catch(i){n[e]();try{t.setItem(r,p)}catch(e){throw n.error()}}}return d})}var n=function e(t,r,i){var a=e.type;return i&&i.type&&i.type in e.types&&(a=i.type),e.types[a](t,r,i||{})};n.types={},n.type=null,n.addType=function(e,t){n.type||(n.type=e),n.types[e]=t,n[e]=function(t,r,i){return i=i||{},i.type=e,n(t,r,i)}},n.error=function(){return"amplify.store quota exceeded"};var u=/^__amplify__/;for(var p in{localStorage:1,sessionStorage:1})try{a()&&(window[p].setItem("__amplify__","x"),window[p].removeItem("__amplify__"),o(p,window[p]))}catch(e){}if(a()&&!n.types.localStorage&&window.globalStorage)try{o("globalStorage",window.globalStorage[window.location.hostname]),"sessionStorage"===n.type&&(n.type="globalStorage")}catch(e){}!function(){if(!n.types.localStorage&&a()){var e=document.createElement("div");e.style.display="none",document.getElementsByTagName("head")[0].appendChild(e);try{e.addBehavior("#default#userdata"),e.load("amplify")}catch(t){return void e.parentNode.removeChild(e)}n.addType("userData",function(t,r,i){e.load("amplify");var a,o,u,p,l,s=r,d=(new Date).getTime();if(!t){for(s={},l=[],p=0;a=e.XMLDocument.documentElement.attributes[p++];)o=JSON.parse(a.value),o.expires&&o.expires<=d?l.push(a.name):s[a.name]=o.data;for(;t=l.pop();)e.removeAttribute(t);return e.save("amplify"),s}if(t=t.replace(/[^\-._0-9A-Za-z\xb7\xc0-\xd6\xd8-\xf6\xf8-\u037d\u037f-\u1fff\u200c-\u200d\u203f\u2040\u2070-\u218f]/g,"-"),t=t.replace(/^-/,"_-"),void 0===r){if(a=e.getAttribute(t),o=a?JSON.parse(a):{expires:-1},!(o.expires&&o.expires<=d))return o.data;e.removeAttribute(t)}else null===r?e.removeAttribute(t):(u=e.getAttribute(t),o=JSON.stringify({data:r,expires:i.expires?d+i.expires:null}),e.setAttribute(t,o));try{e.save("amplify")}catch(r){null===u?e.removeAttribute(t):e.setAttribute(t,u),n.userData();try{e.setAttribute(t,o),e.save("amplify")}catch(r){throw null===u?e.removeAttribute(t):e.setAttribute(t,u),n.error()}}return s})}}(),function(){function e(e){return void 0===e?void 0:JSON.parse(JSON.stringify(e))}var t={},r={};n.addType("memory",function(i,a,o){return i?void 0===a?e(t[i]):(r[i]&&(clearTimeout(r[i]),delete r[i]),null===a?(delete t[i],null):(t[i]=a,o.expires&&(r[i]=setTimeout(function(){delete t[i],delete r[i]},o.expires)),a)):e(t)})}(),r.exports=n},688);