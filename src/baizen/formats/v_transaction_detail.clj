(ns baizen.formats.v-transaction-detail
  (:require [baizen.formats :refer [BaiFormat index-of drop-slash lookup-type-code]]))

(defrecord VTransactionDetail [line]
  BaiFormat
  (fields [_]
    [:record-code :type-code :amount :funds-type
     :value-date :value-time
     :bank-reference-number :customer-reference-number :text])

  (prepare [this line]
    (-> line
        (update-in [(index-of this :customer-reference-number)] drop-slash)
        (update-in [(index-of this :type-code)] lookup-type-code))))
