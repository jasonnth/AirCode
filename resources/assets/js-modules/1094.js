__d(function(t,n,r,e){!function(t,n){"object"==typeof e&&void 0!==r?n(e):"function"==typeof define&&define.amd?define(["exports"],n):n(t.d3=t.d3||{})}(this,function(t){"use strict";function n(t){return new r(t)}function r(t){if(!(n=m.exec(t)))throw new Error("invalid format: "+t);var n,r=n[1]||" ",e=n[2]||">",i=n[3]||"-",o=n[4]||"",a=!!n[5],u=n[6]&&+n[6],c=!!n[7],s=n[8]&&+n[8].slice(1),f=n[9]||"";"n"===f?(c=!0,f="g"):l[f]||(f=""),(a||"0"===r&&"="===e)&&(a=!0,r="0",e="="),this.fill=r,this.align=e,this.sign=i,this.symbol=o,this.zero=a,this.width=u,this.comma=c,this.precision=s,this.type=f}function e(n){return g=M(n),t.format=g.format,t.formatPrefix=g.formatPrefix,g}var i,o=function(t,n){if((r=(t=n?t.toExponential(n-1):t.toExponential()).indexOf("e"))<0)return null;var r,e=t.slice(0,r);return[e.length>1?e[0]+e.slice(2):e,+t.slice(r+1)]},a=function(t){return t=o(Math.abs(t)),t?t[1]:NaN},u=function(t,n){return function(r,e){for(var i=r.length,o=[],a=0,u=t[0],c=0;i>0&&u>0&&(c+u+1>e&&(u=Math.max(1,e-c)),o.push(r.substring(i-=u,i+u)),!((c+=u+1)>e));)u=t[a=(a+1)%t.length];return o.reverse().join(n)}},c=function(t){return function(n){return n.replace(/[0-9]/g,function(n){return t[+n]})}},s=function(t,n){t=t.toPrecision(n);t:for(var r,e=t.length,i=1,o=-1;i<e;++i)switch(t[i]){case".":o=r=i;break;case"0":0===o&&(o=i),r=i;break;case"e":break t;default:o>0&&(o=0)}return o>0?t.slice(0,o)+t.slice(r+1):t},f=function(t,n){var r=o(t,n);if(!r)return t+"";var e=r[0],a=r[1],u=a-(i=3*Math.max(-8,Math.min(8,Math.floor(a/3))))+1,c=e.length;return u===c?e:u>c?e+new Array(u-c+1).join("0"):u>0?e.slice(0,u)+"."+e.slice(u):"0."+new Array(1-u).join("0")+o(t,Math.max(0,n+u-1))[0]},h=function(t,n){var r=o(t,n);if(!r)return t+"";var e=r[0],i=r[1];return i<0?"0."+new Array(-i).join("0")+e:e.length>i+1?e.slice(0,i+1)+"."+e.slice(i+1):e+new Array(i-e.length+2).join("0")},l={"":s,"%":function(t,n){return(100*t).toFixed(n)},b:function(t){return Math.round(t).toString(2)},c:function(t){return t+""},d:function(t){return Math.round(t).toString(10)},e:function(t,n){return t.toExponential(n)},f:function(t,n){return t.toFixed(n)},g:function(t,n){return t.toPrecision(n)},o:function(t){return Math.round(t).toString(8)},p:function(t,n){return h(100*t,n)},r:h,s:f,X:function(t){return Math.round(t).toString(16).toUpperCase()},x:function(t){return Math.round(t).toString(16)}},m=/^(?:(.)?([<>=^]))?([+\-\( ])?([$#])?(0)?(\d+)?(,)?(\.\d+)?([a-z%])?$/i;n.prototype=r.prototype,r.prototype.toString=function(){return this.fill+this.align+this.sign+this.symbol+(this.zero?"0":"")+(null==this.width?"":Math.max(1,0|this.width))+(this.comma?",":"")+(null==this.precision?"":"."+Math.max(0,0|this.precision))+this.type};var g,p=function(t){return t},d=["y","z","a","f","p","n","\xb5","m","","k","M","G","T","P","E","Z","Y"],M=function(t){function r(t){function r(t){var n,r,c,s=v,l=y;if("c"===b)l=w(t)+l,t="";else{t=+t;var m=t<0;if(t=w(Math.abs(t),x),m&&0==+t&&(m=!1),s=(m?"("===u?u:"-":"-"===u||"("===u?"":u)+s,l=l+("s"===b?d[8+i/3]:"")+(m&&"("===u?")":""),j)for(n=-1,r=t.length;++n<r;)if(48>(c=t.charCodeAt(n))||c>57){l=(46===c?f+t.slice(n+1):t.slice(n))+l,t=t.slice(0,n);break}}M&&!g&&(t=o(t,1/0));var k=s.length+t.length+l.length,P=k<p?new Array(p-k+1).join(e):"";switch(M&&g&&(t=o(P+t,P.length?p-l.length:1/0),P=""),a){case"<":t=s+t+l+P;break;case"=":t=s+P+t+l;break;case"^":t=P.slice(0,k=P.length>>1)+s+t+l+P.slice(k);break;default:t=P+s+t+l}return h(t)}t=n(t);var e=t.fill,a=t.align,u=t.sign,c=t.symbol,g=t.zero,p=t.width,M=t.comma,x=t.precision,b=t.type,v="$"===c?s[0]:"#"===c&&/[boxX]/.test(b)?"0"+b.toLowerCase():"",y="$"===c?s[1]:/[%p]/.test(b)?m:"",w=l[b],j=!b||/[defgprs%]/.test(b);return x=null==x?b?6:12:/[gprs]/.test(b)?Math.max(1,Math.min(21,x)):Math.max(0,Math.min(20,x)),r.toString=function(){return t+""},r}function e(t,e){var i=r((t=n(t),t.type="f",t)),o=3*Math.max(-8,Math.min(8,Math.floor(a(e)/3))),u=Math.pow(10,-o),c=d[8+o/3];return function(t){return i(u*t)+c}}var o=t.grouping&&t.thousands?u(t.grouping,t.thousands):p,s=t.currency,f=t.decimal,h=t.numerals?c(t.numerals):p,m=t.percent||"%";return{format:r,formatPrefix:e}};e({decimal:".",thousands:",",grouping:[3],currency:["$",""]});var x=function(t){return Math.max(0,-a(Math.abs(t)))},b=function(t,n){return Math.max(0,3*Math.max(-8,Math.min(8,Math.floor(a(n)/3)))-a(Math.abs(t)))},v=function(t,n){return t=Math.abs(t),n=Math.abs(n)-t,Math.max(0,a(n)-a(t))+1};t.formatDefaultLocale=e,t.formatLocale=M,t.formatSpecifier=n,t.precisionFixed=x,t.precisionPrefix=b,t.precisionRound=v,Object.defineProperty(t,"__esModule",{value:!0})})},1094);