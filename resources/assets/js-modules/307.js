__d(function(e,t,o,n){"use strict";var r=t(266),i=t(60),s=t(308),u=t(309),a=i.PropTypes,c=function(e){function t(o,n){babelHelpers.classCallCheck(this,t);var r=babelHelpers.possibleConstructorReturn(this,e.call(this,o,n));return r._listViewRef=null,r._shouldBounceFirstRowOnMount=!1,r._onScroll=function(e){r.props.dataSource.getOpenRowID()&&r.setState({dataSource:r.state.dataSource.setOpenRowID(null)}),r.props.onScroll&&r.props.onScroll(e)},r._getMaxSwipeDistance=function(e,t,o){return"function"==typeof r.props.maxSwipeDistance?r.props.maxSwipeDistance(e,t,o):r.props.maxSwipeDistance},r._renderRow=function(e,t,o){var n=r.props.renderQuickActions(e,t,o);if(!n)return r.props.renderRow(e,t,o);var i=!1;return r._shouldBounceFirstRowOnMount&&(r._shouldBounceFirstRowOnMount=!1,i=o===r.props.dataSource.getFirstRowID()),babelHelpers.jsx(u,{slideoutView:n,isOpen:e.id===r.props.dataSource.getOpenRowID(),maxSwipeDistance:r._getMaxSwipeDistance(e,t,o),onOpen:function(){return r._onOpen(e.id)},onSwipeEnd:function(){return r._setListViewScrollable(!0)},onSwipeStart:function(){return r._setListViewScrollable(!1)},shouldBounceOnMount:i},o,r.props.renderRow(e,t,o))},r._shouldBounceFirstRowOnMount=r.props.bounceFirstRowOnMount,r.state={dataSource:r.props.dataSource},r}return babelHelpers.inherits(t,e),t.getNewDataSource=function(){return new s({getRowData:function(e,t,o){return e[t][o]},getSectionHeaderData:function(e,t){return e[t]},rowHasChanged:function(e,t){return e!==t},sectionHeaderHasChanged:function(e,t){return e!==t}})},t.prototype.componentWillReceiveProps=function(e){this.state.dataSource.getDataSource()!==e.dataSource.getDataSource()&&this.setState({dataSource:e.dataSource})},t.prototype.render=function(){var e=this;return i.createElement(r,babelHelpers.extends({},this.props,{ref:function(t){e._listViewRef=t},dataSource:this.state.dataSource.getDataSource(),onScroll:this._onScroll,renderRow:this._renderRow}))},t.prototype._setListViewScrollable=function(e){this._listViewRef&&"function"==typeof this._listViewRef.setNativeProps&&this._listViewRef.setNativeProps({scrollEnabled:e})},t.prototype.getScrollResponder=function(){if(this._listViewRef&&"function"==typeof this._listViewRef.getScrollResponder)return this._listViewRef.getScrollResponder()},t.prototype._onOpen=function(e){this.setState({dataSource:this.state.dataSource.setOpenRowID(e)})},t}(i.Component);c.propTypes={bounceFirstRowOnMount:a.bool.isRequired,dataSource:a.instanceOf(s).isRequired,maxSwipeDistance:a.oneOfType([a.number,a.func]).isRequired,renderRow:a.func.isRequired,renderQuickActions:a.func.isRequired},c.defaultProps={bounceFirstRowOnMount:!1,renderQuickActions:function(){return null}},o.exports=c},307);