__d(function(e,r,a,l){Object.defineProperty(l,"__esModule",{value:!0}),l.default={scale:function(e,r,a){var l=babelHelpers.slicedToArray(r,2),n=l[0],t=l[1],i=babelHelpers.slicedToArray(e,2),s=i[0],u=i[1],b=Math.abs(s-u),c=s+b/2,o=b*a/2;return[Math.max(c-o,n),Math.min(c+o,t)]},pan:function(e,r,a){var l=babelHelpers.slicedToArray(e,2),n=l[0],t=l[1],i=babelHelpers.slicedToArray(r,2),s=i[0],u=i[1],b=n+a,c=t+a;if(b>s&&c<u)return[b,c];if(b<s){return[s,s+(t-n)]}if(c>u){return[u-(t-n),u]}return e}}},1302);