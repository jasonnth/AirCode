__d(function(n,t,r,e){function a(){for(var n={},t=f.length,r=0;r<t;r++)n[f[r]]={distance:-1,parent:null};return n}function u(n){var t=a(),r=[n];for(t[n].distance=0;r.length;)for(var e=r.pop(),u=Object.keys(i[e]),c=u.length,o=0;o<c;o++){var f=u[o],p=t[f];-1===p.distance&&(p.distance=t[e].distance+1,p.parent=e,r.unshift(f))}return t}function c(n,t){return function(r){return t(n(r))}}function o(n,t){for(var r=[t[n].parent,n],e=i[t[n].parent][n],a=t[n].parent;t[a].parent;)r.unshift(t[a].parent),e=c(i[t[a].parent][a],e),a=t[a].parent;return e.conversion=r,e}var i=t(429),f=Object.keys(i);r.exports=function(n){for(var t=u(n),r={},e=Object.keys(t),a=e.length,c=0;c<a;c++){var i=e[c];null!==t[i].parent&&(r[i]=o(i,t))}return r}},431);