__d(function(t,i,s,h){!function(t,i){"object"==typeof h&&void 0!==s?i(h):"function"==typeof define&&define.amd?define(["exports"],i):i(t.d3=t.d3||{})}(this,function(t){"use strict";function i(){this._x0=this._y0=this._x1=this._y1=null,this._=""}function s(){return new i}var h=Math.PI,_=2*h,n=_-1e-6;i.prototype=s.prototype={constructor:i,moveTo:function(t,i){this._+="M"+(this._x0=this._x1=+t)+","+(this._y0=this._y1=+i)},closePath:function(){null!==this._x1&&(this._x1=this._x0,this._y1=this._y0,this._+="Z")},lineTo:function(t,i){this._+="L"+(this._x1=+t)+","+(this._y1=+i)},quadraticCurveTo:function(t,i,s,h){this._+="Q"+ +t+","+ +i+","+(this._x1=+s)+","+(this._y1=+h)},bezierCurveTo:function(t,i,s,h,_,n){this._+="C"+ +t+","+ +i+","+ +s+","+ +h+","+(this._x1=+_)+","+(this._y1=+n)},arcTo:function(t,i,s,_,n){t=+t,i=+i,s=+s,_=+_,n=+n;var e=this._x1,o=this._y1,r=s-t,a=_-i,u=e-t,c=o-i,f=u*u+c*c;if(n<0)throw new Error("negative radius: "+n);if(null===this._x1)this._+="M"+(this._x1=t)+","+(this._y1=i);else if(f>1e-6)if(Math.abs(c*r-a*u)>1e-6&&n){var y=s-e,x=_-o,M=r*r+a*a,l=y*y+x*x,d=Math.sqrt(M),v=Math.sqrt(f),p=n*Math.tan((h-Math.acos((M+f-l)/(2*d*v)))/2),b=p/v,w=p/d;Math.abs(b-1)>1e-6&&(this._+="L"+(t+b*u)+","+(i+b*c)),this._+="A"+n+","+n+",0,0,"+ +(c*y>u*x)+","+(this._x1=t+w*r)+","+(this._y1=i+w*a)}else this._+="L"+(this._x1=t)+","+(this._y1=i);else;},arc:function(t,i,s,e,o,r){t=+t,i=+i,s=+s;var a=s*Math.cos(e),u=s*Math.sin(e),c=t+a,f=i+u,y=1^r,x=r?e-o:o-e;if(s<0)throw new Error("negative radius: "+s);null===this._x1?this._+="M"+c+","+f:(Math.abs(this._x1-c)>1e-6||Math.abs(this._y1-f)>1e-6)&&(this._+="L"+c+","+f),s&&(x<0&&(x=x%_+_),x>n?this._+="A"+s+","+s+",0,1,"+y+","+(t-a)+","+(i-u)+"A"+s+","+s+",0,1,"+y+","+(this._x1=c)+","+(this._y1=f):x>1e-6&&(this._+="A"+s+","+s+",0,"+ +(x>=h)+","+y+","+(this._x1=t+s*Math.cos(o))+","+(this._y1=i+s*Math.sin(o))))},rect:function(t,i,s,h){this._+="M"+(this._x0=this._x1=+t)+","+(this._y0=this._y1=+i)+"h"+ +s+"v"+ +h+"h"+-s+"Z"},toString:function(){return this._}},t.path=s,Object.defineProperty(t,"__esModule",{value:!0})})},1111);