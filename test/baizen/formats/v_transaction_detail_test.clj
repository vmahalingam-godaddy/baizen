(ns baizen.formats.v-transaction-detail-test
  (:require [clojure.test :refer :all]
            [baizen.formats :refer :all]
            [baizen.formats.v-transaction-detail :refer :all])
  (:import [baizen.formats.v_transaction_detail VTransactionDetail]))

(deftest v-transaction-detail-test
  (testing "V transaction detail fields" 
    (let [transaction-detail-line ["16" "399" "080" "V" "240116" "" "" "" "MISCELLANEOUS CREDIT~ZZZZZZZZZZ~XXXXX XXXX XXXX~/"]
          transaction-detail (dissect (VTransactionDetail. transaction-detail-line))]
      (is (= "16" (:record-code transaction-detail)))
      (is (= {:code "399" :transaction "CR" :level "Detail" :description "Miscellaneous Credit"}
             (:type-code transaction-detail)))
      (is (= "080" (:amount transaction-detail)))
      (is (= "V" (:funds-type transaction-detail)))
      (is (= "240116" (:value-date transaction-detail)))
      (is (= "" (:value-time transaction-detail)))
      (is (= "MISCELLANEOUS CREDIT~ZZZZZZZZZZ~XXXXX XXXX XXXX~/" (:text transaction-detail))))))
