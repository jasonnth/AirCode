__d(function(e,t,i,n){"use strict";function s(e){if(e&&e.__esModule)return e;var t={};if(null!=e)for(var i in e)Object.prototype.hasOwnProperty.call(e,i)&&(t[i]=e[i]);return t.default=e,t}Object.defineProperty(n,"__esModule",{value:!0}),n.GiftingFlowSuccessfulSentGiftCardImpressionEvent=void 0;var r=t(412),o=function(e){return e&&e.__esModule?e:{default:e}}(r),u=t(694),f=s(u),a=t(698),l=s(a);n.GiftingFlowSuccessfulSentGiftCardImpressionEvent={defaultProps:{schema:"com.airbnb.jitney.event.logging.GiftingFlow:GiftingFlowSuccessfulSentGiftCardImpressionEvent:2.0.0",event_name:"giftingflow_successful_sent_gift_card_impression",page:"gifting_creation",section:"successful_sent_gift_card",operation:1},propTypes:{schema:o.default.string,event_name:o.default.string.isRequired,context:o.default.shape(f.Context.propTypes).isRequired,page:o.default.string.isRequired,section:o.default.string.isRequired,operation:o.default.oneOf(Object.values(l.Operation)).isRequired}}},1901);