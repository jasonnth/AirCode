__d(function(t,n,e,r){(function(){var t,r,i,a=[].slice;if(t=void 0!==e&&null!==e&&null!=e.exports,r=function(t){return"[object Array]"===Object.prototype.toString.call(t)},i=function(t){var n;if(null==t)throw new Error("Can't find moment");return n=function(){function n(n,e,r,i){var a;null==i&&(i={}),"string"!=typeof r&&(i=null!=r?r:{},r=null),"boolean"==typeof i&&(i={allDay:i}),this._oStart=t(n,r,i.parseStrict),this._oEnd=t(e,r,i.parseStrict),this.allDay=null!=(a=i.allDay)&&a,this._mutated()}return n._extend=function(){var t,n,e,r,i,o;for(n=arguments[0],o=2<=arguments.length?a.call(arguments,1):[],e=0,r=o.length;e<r;e++){i=o[e];for(t in i)void 0!==i[t]&&(n[t]=i[t])}return n},n.prototype.start=function(){return this._start.clone()},n.prototype.end=function(){return this._end.clone()},n.prototype.isSame=function(t){return this._start.isSame(this._end,t)},n.prototype.length=function(t,n){return null==n&&(n=!1),this._displayEnd.diff(this._start,t,n)},n.prototype.count=function(t){var n,e;return e=this.start().startOf(t),n=this.end().startOf(t),n.diff(e,t)+1},n.prototype.countInner=function(t){var n,e,r;return e=this._inner(t),r=e[0],n=e[1],r>=n?0:n.diff(r,t)},n.prototype.iterate=function(t,n,e){var r,i,a,o;return a=this._prepIterateInputs(t,n,e),t=a[0],n=a[1],e=a[2],o=this.start().startOf(n),r=this.end().startOf(n),this.allDay&&(r=r.add(1,"d")),i=function(t){return function(){return!t.allDay&&o<=r&&(!e||!o.isSame(r)||t._end.hours()>e)||t.allDay&&o<r}}(this),this._iterateHelper(n,o,i,t)},n.prototype.iterateInner=function(t,n){var e,r,i,a,o;return i=this._prepIterateInputs(t,n),t=i[0],n=i[1],a=this._inner(n,t),o=a[0],e=a[1],r=function(){return o<e},this._iterateHelper(n,o,r,t)},n.prototype.humanizeLength=function(){return this.allDay?this.isSame("d")?"all day":this._start.from(this.end().add(1,"d"),!0):this._start.from(this._end,!0)},n.prototype.asDuration=function(n){var e;return e=this._end.diff(this._start),t.duration(e)},n.prototype.isPast=function(){return this._lastMilli<t()},n.prototype.isFuture=function(){return this._start>t()},n.prototype.isCurrent=function(){return!this.isPast()&&!this.isFuture()},n.prototype.contains=function(n){return t.isMoment(n)||(n=t(n)),this._start<=n&&this._lastMilli>=n},n.prototype.isEmpty=function(){return this._start.isSame(this._displayEnd)},n.prototype.overlaps=function(t){return this._displayEnd.isAfter(t._start)&&this._start.isBefore(t._displayEnd)},n.prototype.engulfs=function(t){return this._start<=t._start&&this._displayEnd>=t._displayEnd},n.prototype.union=function(t){var e,r,i;return e=this.allDay&&t.allDay,i=this._start<t._start?this._start:t._start,r=this._lastMilli>t._lastMilli?e?this._end:this._displayEnd:e?t._end:t._displayEnd,new n(i,r,e)},n.prototype.intersection=function(t){var e,r,i;return e=this.allDay&&t.allDay,i=this._start>t._start?this._start:t._start,r=this._lastMilli<t._lastMilli?e?this._end:this._displayEnd:e?t._end:t._displayEnd,new n(i,r,e)},n.prototype.xor=function(){var t,e,r,i,o,s,l,u,f,h,p,d,m,c,y,_,D,v;for(c=1<=arguments.length?a.call(arguments,0):[],d=0,D=null,_=[],t=function(){var t,n,e;for(e=[],t=0,n=c.length;t<n;t++)p=c[t],p.allDay&&e.push(p);return e}().length===c.length,e=[],y=[this].concat(c),i=s=0,f=y.length;s<f;i=++s)o=y[i],e.push({time:o._start,i:i,type:0}),e.push({time:o._displayEnd,i:i,type:1});for(e=e.sort(function(t,n){return t.time-n.time}),l=0,h=e.length;l<h;l++)m=e[l],1===m.type&&(d-=1),d===m.type&&(D=m.time),d===(m.type+1)%2&&(D&&(u=_[_.length-1],u&&u._end.isSame(D)?(u._oEnd=m.time,u._mutated()):(r=t?m.time.clone().subtract(1,"d"):m.time,v=new n(D,r,t),v.isEmpty()||_.push(v))),D=null),0===m.type&&(d+=1);return _},n.prototype.difference=function(){var t,n,e,r,i,o;for(e=1<=arguments.length?a.call(arguments,0):[],r=this.xor.apply(this,e).map(function(t){return function(n){return t.intersection(n)}}(this)),i=[],t=0,n=r.length;t<n;t++)o=r[t],!o.isEmpty()&&o.isValid()&&i.push(o);return i},n.prototype.split=function(){var n,e,i,o,s,l,u,f,h,p;if(n=1<=arguments.length?a.call(arguments,0):[],i=u=this.start(),t.isDuration(n[0])?e=n[0]:!t.isMoment(n[0])&&!r(n[0])&&"object"==typeof n[0]||"number"==typeof n[0]&&"string"==typeof n[1]?e=t.duration(n[0],n[1]):h=r(n[0])?n[0]:n,h&&(h=function(){var n,e,r;for(r=[],n=0,e=h.length;n<e;n++)f=h[n],r.push(t(f));return r}(),h=function(){var t,n,e;for(e=[],t=0,n=h.length;t<n;t++)l=h[t],l.isValid()&&l>=u&&e.push(l);return e}().sort(function(t,n){return t.valueOf()-n.valueOf()})),e&&0===e.asMilliseconds()||h&&0===h.length)return[this];for(p=[],s=0,o=this._displayEnd;u<o&&(null==h||h[s]);)i=e?u.clone().add(e):h[s].clone(),i=t.min(o,i),u.isSame(i)||p.push(t.twix(u,i)),u=i,s+=1;return!i.isSame(this._displayEnd)&&h&&p.push(t.twix(i,this._displayEnd)),p},n.prototype.divide=function(t){return this.split(this.length()/t,"ms").slice(0,+(t-1)+1||9e9)},n.prototype.isValid=function(){return this._start.isValid()&&this._end.isValid()&&this._start<=this._displayEnd},n.prototype.equals=function(t){return t instanceof n&&this.allDay===t.allDay&&this._start.valueOf()===t._start.valueOf()&&this._end.valueOf()===t._end.valueOf()},n.prototype.toString=function(){return"{start: "+this._start.format()+", end: "+this._end.format()+", allDay: "+(this.allDay?"true":"false")+"}"},n.prototype.toArray=function(t,n,e){var r,i;for(r=this.iterate(t,n,e),i=[];r.hasNext();)i.push(r.next());return i},n.prototype.simpleFormat=function(t,e){var r,i;return r={allDay:"(all day)",template:n.formatTemplate},n._extend(r,e||{}),i=r.template(this._start.format(t),this._end.format(t)),this.allDay&&r.allDay&&(i+=" "+r.allDay),i},n.prototype.format=function(e){var r,i,a,o,s,l,u,f,h,p,d,m,c,y,_,D,v;if(this.isEmpty())return"";for(d=this._start.localeData()._longDateFormat.LT[0],y={groupMeridiems:!0,spaceBeforeMeridiem:!0,showDayOfWeek:!1,hideTime:!1,hideYear:!1,implicitMinutes:!0,implicitDate:!1,implicitYear:!0,yearFormat:"YYYY",monthFormat:"MMM",weekdayFormat:"ddd",dayFormat:"D",meridiemFormat:"A",hourFormat:d,minuteFormat:"mm",allDay:"all day",explicitAllDay:!1,lastNightEndsAt:0,template:n.formatTemplate},n._extend(y,e||{}),l=[],c=y.hourFormat&&"h"===y.hourFormat[0],f=y.lastNightEndsAt>0&&!this.allDay&&this.end().startOf("d").valueOf()===this.start().add(1,"d").startOf("d").valueOf()&&this._start.hours()>12&&this._end.hours()<y.lastNightEndsAt,m=!(y.hideDate||y.implicitDate&&this.start().startOf("d").valueOf()===t().startOf("d").valueOf()&&(this.isSame("d")||f)),r=!(this.allDay||y.hideTime),this.allDay&&this.isSame("d")&&(y.implicitDate||y.explicitAllDay)&&l.push({name:"all day simple",fn:function(){return y.allDay},pre:" ",slot:0}),!m||y.hideYear||y.implicitYear&&this._start.year()===t().year()&&this.isSame("y")||l.push({name:"year",fn:function(t){return t.format(y.yearFormat)},pre:", ",slot:4}),r&&m&&l.push({name:"month-date",fn:function(t){return t.format(y.monthFormat+" "+y.dayFormat)},ignoreEnd:function(){return f},pre:" ",slot:2}),!r&&m&&l.push({name:"month",fn:function(t){return t.format(y.monthFormat)},pre:" ",slot:2}),!r&&m&&l.push({name:"date",fn:function(t){return t.format(y.dayFormat)},pre:" ",slot:3}),m&&y.showDayOfWeek&&l.push({name:"day of week",fn:function(t){return t.format(y.weekdayFormat)},pre:" ",slot:1}),y.groupMeridiems&&c&&!this.allDay&&!y.hideTime&&l.push({name:"meridiem",fn:function(t){return t.format(y.meridiemFormat)},slot:6,pre:y.spaceBeforeMeridiem?" ":""}),this.allDay||y.hideTime||l.push({name:"time",fn:function(t){var n;return n=0===t.minutes()&&y.implicitMinutes&&c?t.format(y.hourFormat):t.format(y.hourFormat+":"+y.minuteFormat),!y.groupMeridiems&&c&&(y.spaceBeforeMeridiem&&(n+=" "),n+=t.format(y.meridiemFormat)),n},slot:5,pre:", "}),D=[],a=[],i=[],v=!0,_=function(t){return function(n){var e,r,s;return s=n.fn(t._start),e=n.ignoreEnd&&n.ignoreEnd()?s:n.fn(t._end),r={format:n,value:function(){return s}},e===s&&v?i.push(r):(v&&(v=!1,i.push({format:{slot:n.slot,pre:""},value:function(){return y.template(o(D),o(a,!0).trim())}})),D.push(r),a.push({format:n,value:function(){return e}}))}}(this),h=0,p=l.length;h<p;h++)s=l[h],_(s);return u=!0,(o=function(t,n){var e,r,i,a,o,s;for(i=!0,s="",a=t.sort(function(t,n){return t.format.slot-n.format.slot}),e=0,r=a.length;e<r;e++)o=a[e],u||(s+=i&&n?" ":o.format.pre),s+=o.value(),u=!1,i=!1;return s})(i)},n.prototype._iterateHelper=function(t,n,e,r){return{next:function(){var i;return e()?(i=n.clone(),n.add(r,t),i):null},hasNext:e}},n.prototype._prepIterateInputs=function(){var n,e,r,i,o,s;return n=1<=arguments.length?a.call(arguments,0):[],"number"==typeof n[0]?n:("string"==typeof n[0]&&(i=n.shift(),e=null!=(o=n.pop())?o:1,n.length&&(r=null!=(s=n[0])&&s)),t.isDuration(n[0])&&(i="ms",e=n[0].as(i)),[e,i,r])},n.prototype._inner=function(t,n){var e,r,i,a,o;return null==t&&(t="ms"),null==n&&(n=1),o=this.start(),i=this._displayEnd.clone(),o>o.clone().startOf(t)&&o.startOf(t).add(n,t),i<i.clone().endOf(t)&&i.startOf(t),r=o.twix(i).asDuration(t),e=r.get(t),a=e%n,i.subtract(a,t),[o,i]},n.prototype._mutated=function(){return this._start=this.allDay?this._oStart.clone().startOf("d"):this._oStart,this._lastMilli=this.allDay?this._oEnd.clone().endOf("d"):this._oEnd,this._end=this.allDay?this._oEnd.clone().startOf("d"):this._oEnd,this._displayEnd=this.allDay?this._end.clone().add(1,"d"):this._end},n}(),n._extend(t.locale(),{_twix:n.defaults}),n.formatTemplate=function(t,n){return t+" - "+n},t.twix=function(){return function(t,n,e){e.prototype=t.prototype;var r=new e,i=t.apply(r,n);return Object(i)===i?i:r}(n,arguments,function(){})},t.fn.twix=function(){return function(t,n,e){e.prototype=t.prototype;var r=new e,i=t.apply(r,n);return Object(i)===i?i:r}(n,[this].concat(a.call(arguments)),function(){})},t.fn.forDuration=function(t,e){return new n(this,this.clone().add(t),e)},t.duration.fn&&(t.duration.fn.afterMoment=function(e,r){return new n(e,t(e).clone().add(this),r)},t.duration.fn.beforeMoment=function(e,r){return new n(t(e).clone().subtract(this),e,r)}),t.twixClass=n,n},t)return e.exports=i(n(562));"function"==typeof define&&define("twix",["moment"],function(t){return i(t)}),this.moment?this.Twix=i(this.moment):"undefined"!=typeof moment&&null!==moment&&(this.Twix=i(moment))}).call(this)},1939);