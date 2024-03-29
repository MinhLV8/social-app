import img1 from "../assets/post/post-1.jpg";
import img2 from "../assets/post/post-2.jpg";
import img3 from "../assets/post/post-3.jpg";
import img4 from "../assets/post/post-4.jpg";
import img5 from "../assets/post/post-5.jpg";

import avt1 from "../assets/person/avt-1.jpg";
import avt2 from "../assets/person/avt-2.png";
import avt3 from "../assets/person/avt-3.jpg";


export const Users = [
    {
        id: 1,
        username: "Minh Lê",
        userAvatar: avt1,
        isOnline: "online"
    }, {
        id: 2,
        username: "Dũng Nguyễn",
        userAvatar: avt2,
        isOnline: "online"
    }, {
        id: 3,
        username: "Trọng Linh",
        userAvatar: avt3, isOnline: "10 phút"
    }, {
        id: 4,
        username: "Hồ Sỹ Thành",
        userAvatar: img1, isOnline: "1 giờ"
    }, {
        id: 5,
        username: "Ngọc Ánh",
        userAvatar: img1, isOnline: "30 phút"
    }, {
        id: 5,
        username: "Đinh Phú Việt",
        userAvatar: img1, isOnline: "online"
    }
]
export const PostsData = [
    {
        id: 1,
        images: [img1, img2, img3, img4, img5],
        userId: 1,
        privacy: 1,
        fullname: 'Minh Lê',
        caption: "Một điểm Camping🏕  săn mây tại Tây Nguyên mà ít ai biết ☺️",
        likes: 2300324,
        comments: 326272,
        shares: 54236,
        times: 1656670238000,
        liked: true
    },
    {
        id: 2,
        images: [img2, img3, img4, img5],
        userId: 2,
        privacy: 2,
        fullname: 'Ngọc Ánh',
        caption: "Có thằng bạn từ Mỹ sang Việt Nam, cho đi leo núi tắm thác và bắt làm nông dân, cho hút cả thuốc lào và ăn bún chả nữa, Ben nói: I love Viet Nam:)",
        likes: 243243,
        comments: 532423446,
        shares: 59,
        times: 1659262238000,
        liked: false

    },
    {
        id: 3,
        userId: 3,
        images: [img3, img4, img5],
        privacy: 1,
        fullname: "Salena Gomez",
        caption: "Một chút bình yên miền biển 🍃 🌊",
        likes: 4563,
        comments: 52342346,
        shares: 46,
        liked: false,
        times: 1659262238000,
    },
    {
        id: 4,
        userId: 4,
        images: [img4, img5],
        privacy: 1,
        fullname: "Salena Gomez",
        caption: "Một chút bình yên miền biển 🍃 🌊",
        likes: 34625,
        comments: 2537757,
        shares: 2386423,
        liked: false,
        times: 1659262238000,
    },
    {
        id: 5,
        userId: 5,
        images: [img4],
        privacy: 1,
        fullname: "Salena Gomez",
        caption: "Một chút bình yên miền biển 🍃 🌊",
        likes: 95346,
        comments: 344567,
        shares: 8765,
        liked: false,
        times: 1659262238000,
    }
]
export const PostComments = [
    {
        id: 1,
        postId: 1,
        comment: "Tây nguyên đẹp thật",
        userId: 1,
        times: 1659338583000,
    }, {
        id: 2,
        postId: 1,
        userId: 2,
        times: 1659252183000,
        comment: "Hình như dãy núi Ngọc Linh",
    }
]