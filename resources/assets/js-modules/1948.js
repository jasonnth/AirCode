__d(function(t,r,e,n){Object.defineProperty(n,"__esModule",{value:!0}),n.insertIntoSortedListAssumingNearBack=n.insertIntoSortedListAssumingNearFront=n.fromJSWithDepth=n.mergeWithDepth=n.mergeListWithDepth=n.mergeIn=n.merge=void 0;var i=r(417),s=n.merge=function(t,r){return Array.isArray(t)?t.concat(r):t.withMutations(function(t){for(var e=t,n=r?Object.keys(r):[],i=0;i<n.length;i++){var s=n[i];e=e.set(s,r[s])}return e})},o=(n.mergeIn=function(t,r,e){return t.updateIn(r,function(t){return s(t,e)})},n.mergeListWithDepth=function t(r,e,n){return r.withMutations(function(r){for(var s=r,o=0;o<n.length;o++){var a=n[o];s=e>0&&Array.isArray(a)?s.set(o,t(s.get(o,(0,i.List)()),e-1,a)):e>0&&"object"==typeof a?s.set(o,u((0,i.Map)(),e-1,a)):s.set(o,a)}return s})}),u=n.mergeWithDepth=function t(r,e,n){return r.withMutations(function(r){for(var s=r,u=Object.keys(n),a=0;a<u.length;a++){var f=u[a],c=n[f];s=e>0&&Array.isArray(c)?s.set(f,o(s.get(f,(0,i.List)()),e-1,c)):e>0&&"object"==typeof c?s.set(f,t(s.get(f,(0,i.Map)()),e-1,c)):s.set(f,n[f])}return s})};n.fromJSWithDepth=function(t,r){return Array.isArray(r)?o((0,i.List)(),t,r):u((0,i.Map)(),t,r)},n.insertIntoSortedListAssumingNearFront=function(t,r,e){var n=e(r),i=t.findIndex(function(t){return n<=e(t)});return i>=0?t.insert(i,r):t.push(r)},n.insertIntoSortedListAssumingNearBack=function(t,r,e){var n=e(r),i=t.findLastIndex(function(t){return n>=e(t)});return t.insert(i+1,r)}},1948);