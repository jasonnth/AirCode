__d(function(n,u,t,e){"use strict";var r=(u(55),u(56)),c={_currentlyFocusedID:null,currentlyFocusedField:function(){return this._currentlyFocusedID},focusTextInput:function(n){this._currentlyFocusedID!==n&&null!==n&&(this._currentlyFocusedID=n,r.dispatchViewManagerCommand(n,r.AndroidTextInput.Commands.focusTextInput,null))},blurTextInput:function(n){this._currentlyFocusedID===n&&null!==n&&(this._currentlyFocusedID=null,r.dispatchViewManagerCommand(n,r.AndroidTextInput.Commands.blurTextInput,null))}};t.exports=c},54);