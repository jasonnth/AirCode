__d(function(e,t,o,n){"use strict";function a(e){return!(!e||"function"!=typeof e.attachRef||"function"!=typeof e.detachRef)}var r=t(18),c={addComponentAsRefTo:function(e,t,o,n){r(a(o),"addComponentAsRefTo(...): Only a ReactOwner can have refs. You might be adding a ref to a component that was not created inside a component's `render` method, or you have multiple copies of React loaded (details: https://fb.me/react-refs-must-have-owner)."),o.attachRef(t,e,n)},removeComponentAsRefFrom:function(e,t,o){r(a(o),"removeComponentAsRefFrom(...): Only a ReactOwner can have refs. You might be removing a ref to a component that was not created inside a component's `render` method, or you have multiple copies of React loaded (details: https://fb.me/react-refs-must-have-owner).");var n=o.getPublicInstance();n&&n.refs[t]===e.getPublicInstance()&&o.detachRef(t)}};o.exports=c},142);