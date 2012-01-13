(ns euler.core)

; Helper functions

(def fib (lazy-cat [1 2]
    (map +' fib (rest fib))))

(defn factors [x]
    (filter #(= 0 (rem x %)) (range 2 (+ 1 (/ x 2)))))
    
(defn reverse-num [x]
    (loop [num x res 0 index (Math/round (Math/floor (Math/log10 x)))]
        (if (< num 1) (Math/round res)
            (recur (quot num 10) (+ res (* (Math/pow 10 index) (rem num 10))) (- index 1)))))

(defn palindrome? [x]
    (= x (reverse-num x)))
    
    
(defn prime? [x]
    (case x
        0 false
        1 false
        2 true
        (= 0 (count (filter #(= 0 (rem x %)) (range 2 (+ 1 (Math/sqrt x))))))))

(defn primes-sieve [n]
    (let [integers (range 2 (+ n 1))
          divisible? (fn [x y] (= 0 (rem x y)))]
        (loop [prime 2 remaining (doall (remove #(divisible? % 2) integers)) primes []]
            (if (empty? remaining)
                primes
                (recur (first remaining) (doall (remove #(divisible? % prime) remaining)) (cons prime primes))))))
    

(defn nth-prime [n]
    (loop [number 2 index 1]
        (if (prime? number)
            (if (= n index) number (recur (inc number) (inc index)))
            (recur (inc number) index))))

(defn digit-product [number]
    (loop [n number prod 1]
        (if (= n 0)
            prod
            (recur (int (/ n 10)) (* prod (rem n 10))))))
    
(defn pythagorean-triplet? [a b c]
    (and (< a b c) (= (* c c) (+ (* a a) (* b b)))))

; Problem solutions

(defn problem1 []
    (reduce + (filter #(or (= 0 (rem % 3)) (= 0 (rem % 5))) (range 1000))))

(defn problem2 []
    (reduce +' (filter even? (take-while #(> 4000000 %) fib))))

(defn problem3 []
    (max (filter prime? (factors 600851475143))))

(defn problem4 []
    (let [threeDig (range 100 1000)]
        (reduce max (filter palindrome? (for [x threeDig y threeDig] (* x y))))))

(defn problem5 []
    (loop [num 1]
        (if (and (= 0 (rem num 20)) (= 0 (rem num 19)) (= 0 (rem num 18)) (= 0 (rem num 17)) (= 0 (rem num 16)) (= 0 (rem num 15)) (= 0 (rem num 14)) (= 0 (rem num 13)) (= 0 (rem num 12)) (= 0 (rem num 11))) num (recur (inc num)))))

(defn problem6 []
    (let [hundred (range 1 101)]
        (- (Math/round (Math/pow (reduce + hundred) 2)) (reduce + (map #(* % %) hundred)))))

(defn problem7 []
    (nth-prime 10001))

(defn problem8 []
    (let [number 7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450]
        (reduce max
            (map digit-product
                (loop [grouplist [] remaining number]
                    (if (< remaining 100000)
                        grouplist
                        (recur (cons (int (rem remaining 100000)) grouplist) (/ remaining 10))))))))

(defn problem9 []
    (let [thousand (range 1 1001)]
        (apply * (flatten (filter #(apply pythagorean-triplet? %) (for [a thousand b thousand] (list a b (- 1000 a b))))))))


(defn problem10 []
    (reduce +' (filter prime? (range 2 2000001))))
