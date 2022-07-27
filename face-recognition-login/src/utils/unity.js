export default function (promise) {
    return promise.then(value => [value, null]).catch(reason => [null, reason])
}