import img1 from "../assets/post/post-1.jpg";
import img2 from "../assets/post/post-2.jpg";
import img3 from "../assets/post/post-3.jpg";
export const Users = [
    {
        id: 1,
        username: "Minh Lê",
        userAvatar: img1,
        isOnline: "online"
    }, {
        id: 2,
        username: "Dũng Nguyễn",
        userAvatar: img2,
        isOnline: "online"
    }, {
        id: 3,
        username: "Trọng Linh",
        userAvatar: img3, isOnline: "10 phút"
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
        img: img1,
        userId: 1,
        privacy: 1,
        name: 'Tzuyu',
        desc: "Một điểm Camping🏕  săn mây tại Tây Nguyên mà ít ai biết ☺️",
        likes: 2300,
        times: 1656670238000,
        liked: true
    },
    {
        id: 2,
        img: img2,
        userId: 1,
        privacy: 2,
        name: 'Maryam',
        desc: "Có thằng bạn từ Mỹ sang Việt Nam, cho đi leo núi tắm thác và bắt làm nông dân, cho hút cả thuốc lào và ăn bún chả nữa, Ben nói: I love Viet Nam:)",
        likes: 2300,
        times: 1659262238000,
        liked: false

    },
    {
        id: 3,
        userId: 1,
        img: img3,
        privacy: 1,
        name: "Salena Gomez",
        desc: "Một chút bình yên miền biển 🍃 🌊",
        likes: 800,
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