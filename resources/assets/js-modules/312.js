__d(function(t,s,o,f){"use strict";var e=s(313),h=function(){function t(s,o){babelHelpers.classCallCheck(this,t),this._anchorOffset=s,this._focusOffset=o,this._hasFocus=!1}return t.prototype.update=function(t,s){this._anchorOffset===t&&this._focusOffset===s||(this._anchorOffset=t,this._focusOffset=s,this.emit("update"))},t.prototype.constrainLength=function(t){this.update(Math.min(this._anchorOffset,t),Math.min(this._focusOffset,t))},t.prototype.focus=function(){this._hasFocus||(this._hasFocus=!0,this.emit("focus"))},t.prototype.blur=function(){this._hasFocus&&(this._hasFocus=!1,this.emit("blur"))},t.prototype.hasFocus=function(){return this._hasFocus},t.prototype.isCollapsed=function(){return this._anchorOffset===this._focusOffset},t.prototype.isBackward=function(){return this._anchorOffset>this._focusOffset},t.prototype.getAnchorOffset=function(){return this._hasFocus?this._anchorOffset:null},t.prototype.getFocusOffset=function(){return this._hasFocus?this._focusOffset:null},t.prototype.getStartOffset=function(){return this._hasFocus?Math.min(this._anchorOffset,this._focusOffset):null},t.prototype.getEndOffset=function(){return this._hasFocus?Math.max(this._anchorOffset,this._focusOffset):null},t.prototype.overlaps=function(t,s){return this.hasFocus()&&this.getStartOffset()<=s&&t<=this.getEndOffset()},t}();e(h,{blur:!0,focus:!0,update:!0}),o.exports=h},312);