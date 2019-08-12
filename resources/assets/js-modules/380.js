__d(function(t,n,e,r){"use strict";function i(t){var n={};return a(t,function(t,e){a(t,function(t){n[t]=e})}),n}function s(t){var n=i(m);return n[t]||n[g.call(t,/-/,1)[0]]||n.en}function o(t,n){return v[s(t)](n)}function c(t,n,e){if("string"!=typeof t)throw new TypeError("Polyglot.transformPhrase expects argument #1 to be string");if(null==n)return t;var r=t,i="number"==typeof n?{smart_count:n}:n;if(null!=i.smart_count&&r){var s=g.call(r,d);r=h(s[o(e||"en",i.smart_count)]||s[0])}return r=y.call(r,_,function(t,n){return f(i,n)&&null!=i[n]?y.call(i[n],b,M):t})}function u(t){var n=t||{};this.phrases={},this.extend(n.phrases||{}),this.currentLocale=n.locale||"en";var e=n.allowMissing?c:null;this.onMissingKey="function"==typeof n.onMissingKey?n.onMissingKey:e,this.warn=n.warn||p}var a=n(381),l=n(383),f=n(384),h=n(387),p=function(t){l(!1,t)},y=String.prototype.replace,g=String.prototype.split,d="||||",v={arabic:function(t){return t<3?t:t%100>=3&&t%100<=10?3:t%100>=11?4:5},chinese:function(){return 0},german:function(t){return 1!==t?1:0},french:function(t){return t>1?1:0},russian:function(t){return t%10==1&&t%100!=11?0:t%10>=2&&t%10<=4&&(t%100<10||t%100>=20)?1:2},czech:function(t){return 1===t?0:t>=2&&t<=4?1:2},polish:function(t){return 1===t?0:t%10>=2&&t%10<=4&&(t%100<10||t%100>=20)?1:2},icelandic:function(t){return t%10!=1||t%100==11?1:0}},m={arabic:["ar"],chinese:["fa","id","ja","ko","lo","ms","th","tr","zh"],german:["da","de","en","es","fi","el","he","hu","it","nl","no","pt","sv"],french:["fr","tl","pt-br"],russian:["hr","ru","lt"],czech:["cs","sk"],polish:["pl"],icelandic:["is"]},b=/\$/g,M="$$",_=/%\{(.*?)\}/g;u.prototype.locale=function(t){return t&&(this.currentLocale=t),this.currentLocale},u.prototype.extend=function(t,n){a(t,function(t,e){var r=n?n+"."+e:e;"object"==typeof t?this.extend(t,r):this.phrases[r]=t},this)},u.prototype.unset=function(t,n){"string"==typeof t?delete this.phrases[t]:a(t,function(t,e){var r=n?n+"."+e:e;"object"==typeof t?this.unset(t,r):delete this.phrases[r]},this)},u.prototype.clear=function(){this.phrases={}},u.prototype.replace=function(t){this.clear(),this.extend(t)},u.prototype.t=function(t,n){var e,r,i=null==n?{}:n;if("string"==typeof this.phrases[t])e=this.phrases[t];else if("string"==typeof i._)e=i._;else if(this.onMissingKey){var s=this.onMissingKey;r=s(t,i,this.currentLocale)}else this.warn('Missing translation for key: "'+t+'"'),r=t;return"string"==typeof e&&(r=c(e,i,this.currentLocale)),r},u.prototype.has=function(t){return f(this.phrases,t)},u.transformPhrase=c,e.exports=u},380);