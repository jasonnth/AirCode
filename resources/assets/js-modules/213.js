__d(function(t,e,o,n){"use strict";var r=e(208),i=e(209),s=e(212);o.exports=function(t){function e(t){t._map=new r,t.size=t._map.size}if(!i("Set"))return t.Set;var o=function(){function t(o){if(babelHelpers.classCallCheck(this,t),null==this||"object"!=typeof this&&"function"!=typeof this)throw new TypeError("Wrong set object type.");if(e(this),null!=o)for(var n,r=s(o);!(n=r.next()).done;)this.add(n.value)}return t.prototype.add=function(t){return this._map.set(t,t),this.size=this._map.size,this},t.prototype.clear=function(){e(this)},t.prototype.delete=function(t){var e=this._map.delete(t);return this.size=this._map.size,e},t.prototype.entries=function(){return this._map.entries()},t.prototype.forEach=function(t){for(var e,o=arguments[1],n=this._map.keys();!(e=n.next()).done;)t.call(o,e.value,e.value,this)},t.prototype.has=function(t){return this._map.has(t)},t.prototype.values=function(){return this._map.values()},t}();return o.prototype[s.ITERATOR_SYMBOL]=o.prototype.values,o.prototype.keys=o.prototype.values,o}(Function("return this")())},213);