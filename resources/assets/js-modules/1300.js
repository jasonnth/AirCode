__d(function(e,t,n,i){!function(e,t){"object"==typeof i&&void 0!==n?t(i):"function"==typeof define&&define.amd?define(["exports"],t):t(e.d3=e.d3||{})}(this,function(e){"use strict";function t(e){return e[0]}function n(e){return e[1]}function i(){this._=null}function r(e){e.U=e.C=e.L=e.R=e.P=e.N=null}function f(e,t){var n=t,i=t.R,r=n.U;r?r.L===n?r.L=i:r.R=i:e._=i,i.U=r,n.U=i,n.R=i.L,n.R&&(n.R.U=n),i.L=n}function u(e,t){var n=t,i=t.L,r=n.U;r?r.L===n?r.L=i:r.R=i:e._=i,i.U=r,n.U=i,n.L=i.R,n.L&&(n.L.U=n),i.R=n}function s(e){for(;e.L;)e=e.L;return e}function l(e,t,n,i){var r=[null,null],f=D.push(r)-1;return r.left=e,r.right=t,n&&o(r,e,t,n),i&&o(r,t,e,i),O[e.index].halfedges.push(f),O[t.index].halfedges.push(f),r}function a(e,t,n){var i=[t,n];return i.left=e,i}function o(e,t,n,i){e[0]||e[1]?e.left===n?e[1]=i:e[0]=i:(e[0]=i,e.left=t,e.right=n)}function h(e,t,n,i,r){var f,u=e[0],s=e[1],l=u[0],a=u[1],o=s[0],h=s[1],c=0,d=1,v=o-l,g=h-a;if(f=t-l,v||!(f>0)){if(f/=v,v<0){if(f<c)return;f<d&&(d=f)}else if(v>0){if(f>d)return;f>c&&(c=f)}if(f=i-l,v||!(f<0)){if(f/=v,v<0){if(f>d)return;f>c&&(c=f)}else if(v>0){if(f<c)return;f<d&&(d=f)}if(f=n-a,g||!(f>0)){if(f/=g,g<0){if(f<c)return;f<d&&(d=f)}else if(g>0){if(f>d)return;f>c&&(c=f)}if(f=r-a,g||!(f<0)){if(f/=g,g<0){if(f>d)return;f>c&&(c=f)}else if(g>0){if(f<c)return;f<d&&(d=f)}return!(c>0||d<1)||(c>0&&(e[0]=[l+c*v,a+c*g]),d<1&&(e[1]=[l+d*v,a+d*g]),!0)}}}}}function c(e,t,n,i,r){var f=e[1];if(f)return!0;var u,s,l=e[0],a=e.left,o=e.right,h=a[0],c=a[1],d=o[0],v=o[1],g=(h+d)/2,C=(c+v)/2;if(v===c){if(g<t||g>=i)return;if(h>d){if(l){if(l[1]>=r)return}else l=[g,n];f=[g,r]}else{if(l){if(l[1]<n)return}else l=[g,r];f=[g,n]}}else if(u=(h-d)/(v-c),s=C-u*g,u<-1||u>1)if(h>d){if(l){if(l[1]>=r)return}else l=[(n-s)/u,n];f=[(r-s)/u,r]}else{if(l){if(l[1]<n)return}else l=[(r-s)/u,r];f=[(n-s)/u,n]}else if(c<v){if(l){if(l[0]>=i)return}else l=[t,u*t+s];f=[i,u*i+s]}else{if(l){if(l[0]<t)return}else l=[i,u*i+s];f=[t,u*t+s]}return e[0]=l,e[1]=f,!0}function d(e,t,n,i){for(var r,f=D.length;f--;)c(r=D[f],e,t,n,i)&&h(r,e,t,n,i)&&(Math.abs(r[0][0]-r[1][0])>H||Math.abs(r[0][1]-r[1][1])>H)||delete D[f]}function v(e){return O[e.index]={site:e,halfedges:[]}}function g(e,t){var n=e.site,i=t.left,r=t.right;return n===r&&(r=i,i=n),r?Math.atan2(r[1]-i[1],r[0]-i[0]):(n===i?(i=t[1],r=t[0]):(i=t[0],r=t[1]),Math.atan2(i[0]-r[0],r[1]-i[1]))}function C(e,t){return t[+(t.left!==e.site)]}function p(e,t){return t[+(t.left===e.site)]}function L(){for(var e,t,n,i,r=0,f=O.length;r<f;++r)if((e=O[r])&&(i=(t=e.halfedges).length)){var u=new Array(i),s=new Array(i);for(n=0;n<i;++n)u[n]=n,s[n]=g(e,D[t[n]]);for(u.sort(function(e,t){return s[t]-s[e]}),n=0;n<i;++n)s[n]=t[u[n]];for(n=0;n<i;++n)t[n]=s[n]}}function R(e,t,n,i){var r,f,u,s,l,o,h,c,d,v,g,L,R=O.length,y=!0;for(r=0;r<R;++r)if(f=O[r]){for(u=f.site,l=f.halfedges,s=l.length;s--;)D[l[s]]||l.splice(s,1);for(s=0,o=l.length;s<o;)v=p(f,D[l[s]]),g=v[0],L=v[1],h=C(f,D[l[++s%o]]),c=h[0],d=h[1],(Math.abs(g-c)>H||Math.abs(L-d)>H)&&(l.splice(s,0,D.push(a(u,v,Math.abs(g-e)<H&&i-L>H?[e,Math.abs(c-e)<H?d:i]:Math.abs(L-i)<H&&n-g>H?[Math.abs(d-i)<H?c:n,i]:Math.abs(g-n)<H&&L-t>H?[n,Math.abs(c-n)<H?d:t]:Math.abs(L-t)<H&&g-e>H?[Math.abs(d-t)<H?c:e,t]:null))-1),++o);o&&(y=!1)}if(y){var b,M,U,N=1/0;for(r=0,y=null;r<R;++r)(f=O[r])&&(u=f.site,b=u[0]-e,M=u[1]-t,(U=b*b+M*M)<N&&(N=U,y=f));if(y){var P=[e,t],_=[e,i],x=[n,i],k=[n,t];y.halfedges.push(D.push(a(u=y.site,P,_))-1,D.push(a(u,_,x))-1,D.push(a(u,x,k))-1,D.push(a(u,k,P))-1)}}for(r=0;r<R;++r)(f=O[r])&&(f.halfedges.length||delete O[r])}function y(){r(this),this.x=this.y=this.arc=this.site=this.cy=null}function b(e){var t=e.P,n=e.N;if(t&&n){var i=t.site,r=e.site,f=n.site;if(i!==f){var u=r[0],s=r[1],l=i[0]-u,a=i[1]-s,o=f[0]-u,h=f[1]-s,c=2*(l*h-a*o);if(!(c>=-I)){var d=l*l+a*a,v=o*o+h*h,g=(h*d-a*v)/c,C=(l*v-o*d)/c,p=F.pop()||new y;p.arc=e,p.site=r,p.x=g+u,p.y=(p.cy=C+s)+Math.sqrt(g*g+C*C),e.circle=p;for(var L=null,R=B._;R;)if(p.y<R.y||p.y===R.y&&p.x<=R.x){if(!R.L){L=R.P;break}R=R.L}else{if(!R.R){L=R;break}R=R.R}B.insert(L,p),L||(E=p)}}}}function M(e){var t=e.circle;t&&(t.P||(E=t.N),B.remove(t),F.push(t),r(t),e.circle=null)}function U(){r(this),this.edge=this.site=this.circle=null}function N(e){var t=G.pop()||new U;return t.site=e,t}function P(e){M(e),z.remove(e),G.push(e),r(e)}function _(e){var t=e.circle,n=t.x,i=t.cy,r=[n,i],f=e.P,u=e.N,s=[e];P(e);for(var a=f;a.circle&&Math.abs(n-a.circle.x)<H&&Math.abs(i-a.circle.cy)<H;)f=a.P,s.unshift(a),P(a),a=f;s.unshift(a),M(a);for(var h=u;h.circle&&Math.abs(n-h.circle.x)<H&&Math.abs(i-h.circle.cy)<H;)u=h.N,s.push(h),P(h),h=u;s.push(h),M(h);var c,d=s.length;for(c=1;c<d;++c)h=s[c],a=s[c-1],o(h.edge,a.site,h.site,r);a=s[0],h=s[d-1],h.edge=l(a.site,h.site,null,r),b(a),b(h)}function x(e){for(var t,n,i,r,f=e[0],u=e[1],s=z._;s;)if((i=k(s,u)-f)>H)s=s.L;else{if(!((r=f-w(s,u))>H)){i>-H?(t=s.P,n=s):r>-H?(t=s,n=s.N):t=n=s;break}if(!s.R){t=s;break}s=s.R}v(e);var a=N(e);if(z.insert(t,a),t||n){if(t===n)return M(t),n=N(t.site),z.insert(a,n),a.edge=n.edge=l(t.site,a.site),b(t),void b(n);if(!n)return void(a.edge=l(t.site,a.site));M(t),M(n);var h=t.site,c=h[0],d=h[1],g=e[0]-c,C=e[1]-d,p=n.site,L=p[0]-c,R=p[1]-d,y=2*(g*R-C*L),U=g*g+C*C,P=L*L+R*R,_=[(R*U-C*P)/y+c,(g*P-L*U)/y+d];o(n.edge,h,p,_),a.edge=l(h,e,null,_),n.edge=l(e,p,null,_),b(t),b(n)}}function k(e,t){var n=e.site,i=n[0],r=n[1],f=r-t;if(!f)return i;var u=e.P;if(!u)return-1/0;n=u.site;var s=n[0],l=n[1],a=l-t;if(!a)return s;var o=s-i,h=1/f-1/a,c=o/a;return h?(-c+Math.sqrt(c*c-2*h*(o*o/(-2*a)-l+a/2+r-f/2)))/h+i:(i+s)/2}function w(e,t){var n=e.N;if(n)return k(n,t);var i=e.site;return i[1]===t?i[0]:1/0}function m(e,t,n){return(e[0]-n[0])*(t[1]-e[1])-(e[0]-t[0])*(n[1]-e[1])}function A(e,t){return t[1]-e[1]||t[0]-e[0]}function j(e,t){var n,r,f,u=e.sort(A).pop();for(D=[],O=new Array(e.length),z=new i,B=new i;;)if(f=E,u&&(!f||u[1]<f.y||u[1]===f.y&&u[0]<f.x))u[0]===n&&u[1]===r||(x(u),n=u[0],r=u[1]),u=e.pop();else{if(!f)break;_(f.arc)}if(L(),t){var s=+t[0][0],l=+t[0][1],a=+t[1][0],o=+t[1][1];d(s,l,a,o),R(s,l,a,o)}this.edges=D,this.cells=O,z=B=D=O=null}var q=function(e){return function(){return e}};i.prototype={constructor:i,insert:function(e,t){var n,i,r;if(e){if(t.P=e,t.N=e.N,e.N&&(e.N.P=t),e.N=t,e.R){for(e=e.R;e.L;)e=e.L;e.L=t}else e.R=t;n=e}else this._?(e=s(this._),t.P=null,t.N=e,e.P=e.L=t,n=e):(t.P=t.N=null,this._=t,n=null);for(t.L=t.R=null,t.U=n,t.C=!0,e=t;n&&n.C;)i=n.U,n===i.L?(r=i.R,r&&r.C?(n.C=r.C=!1,i.C=!0,e=i):(e===n.R&&(f(this,n),e=n,n=e.U),n.C=!1,i.C=!0,u(this,i))):(r=i.L,r&&r.C?(n.C=r.C=!1,i.C=!0,e=i):(e===n.L&&(u(this,n),e=n,n=e.U),n.C=!1,i.C=!0,f(this,i))),n=e.U;this._.C=!1},remove:function(e){e.N&&(e.N.P=e.P),e.P&&(e.P.N=e.N),e.N=e.P=null;var t,n,i,r=e.U,l=e.L,a=e.R;if(n=l?a?s(a):l:a,r?r.L===e?r.L=n:r.R=n:this._=n,l&&a?(i=n.C,n.C=e.C,n.L=l,l.U=n,n!==a?(r=n.U,n.U=e.U,e=n.R,r.L=e,n.R=a,a.U=n):(n.U=r,r=n,e=n.R)):(i=e.C,e=n),e&&(e.U=r),!i){if(e&&e.C)return void(e.C=!1);do{if(e===this._)break;if(e===r.L){if(t=r.R,t.C&&(t.C=!1,r.C=!0,f(this,r),t=r.R),t.L&&t.L.C||t.R&&t.R.C){t.R&&t.R.C||(t.L.C=!1,t.C=!0,u(this,t),t=r.R),t.C=r.C,r.C=t.R.C=!1,f(this,r),e=this._;break}}else if(t=r.L,t.C&&(t.C=!1,r.C=!0,u(this,r),t=r.L),t.L&&t.L.C||t.R&&t.R.C){t.L&&t.L.C||(t.R.C=!1,t.C=!0,f(this,t),t=r.L),t.C=r.C,r.C=t.L.C=!1,u(this,r),e=this._;break}t.C=!0,e=r,r=r.U}while(!e.C);e&&(e.C=!1)}}};var E,z,O,B,D,F=[],G=[],H=1e-6,I=1e-12;j.prototype={constructor:j,polygons:function(){var e=this.edges;return this.cells.map(function(t){var n=t.halfedges.map(function(n){return C(t,e[n])});return n.data=t.site.data,n})},triangles:function(){var e=[],t=this.edges;return this.cells.forEach(function(n,i){if(f=(r=n.halfedges).length)for(var r,f,u,s=n.site,l=-1,a=t[r[f-1]],o=a.left===s?a.right:a.left;++l<f;)u=o,a=t[r[l]],o=a.left===s?a.right:a.left,u&&o&&i<u.index&&i<o.index&&m(s,u,o)<0&&e.push([s.data,u.data,o.data])}),e},links:function(){return this.edges.filter(function(e){return e.right}).map(function(e){return{source:e.left.data,target:e.right.data}})},find:function(e,t,n){for(var i,r,f=this,u=f._found||0,s=f.cells.length;!(r=f.cells[u]);)if(++u>=s)return null;var l=e-r.site[0],a=t-r.site[1],o=l*l+a*a;do{r=f.cells[i=u],u=null,r.halfedges.forEach(function(n){var i=f.edges[n],s=i.left;if(s!==r.site&&s||(s=i.right)){var l=e-s[0],a=t-s[1],h=l*l+a*a;h<o&&(o=h,u=s.index)}})}while(null!==u);return f._found=i,null==n||o<=n*n?r.site:null}};var J=function(){function e(e){return new j(e.map(function(t,n){var f=[Math.round(i(t,n,e)/H)*H,Math.round(r(t,n,e)/H)*H];return f.index=n,f.data=t,f}),f)}var i=t,r=n,f=null;return e.polygons=function(t){return e(t).polygons()},e.links=function(t){return e(t).links()},e.triangles=function(t){return e(t).triangles()},e.x=function(t){return arguments.length?(i="function"==typeof t?t:q(+t),e):i},e.y=function(t){return arguments.length?(r="function"==typeof t?t:q(+t),e):r},e.extent=function(t){return arguments.length?(f=null==t?null:[[+t[0][0],+t[0][1]],[+t[1][0],+t[1][1]]],e):f&&[[f[0][0],f[0][1]],[f[1][0],f[1][1]]]},e.size=function(t){return arguments.length?(f=null==t?null:[[0,0],[+t[0],+t[1]]],e):f&&[f[1][0]-f[0][0],f[1][1]-f[0][1]]},e};e.voronoi=J,Object.defineProperty(e,"__esModule",{value:!0})})},1300);