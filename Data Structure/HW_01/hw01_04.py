import math

# Class of polygon 
class Polygon():
    def __init__(self, vertices):
        self.vertices = vertices
        pass

    def isConvex(self):
        n = len(self.vertices)

        # 檢查邊數
        if n < 3:
            return False
        
        sign = 0  # 記錄叉積正負號（0 表示尚未確定）

        # 遍歷每個頂點
        for i in range(n):
            # 獲取三個連續頂點
            p1 = self.vertices[(i - 1) % n]  # 前一個頂點
            p2 = self.vertices[i]             # 當前頂點
            p3 = self.vertices[(i + 1) % n]  # 下一個頂點
            
            # 計算向量
            vec1 = (p2[0] - p1[0], p2[1] - p1[1])  # p1 -> p2
            vec2 = (p3[0] - p2[0], p3[1] - p2[1])  # p2 -> p3
            
            # 計算叉積
            cross = vec1[0] * vec2[1] - vec1[1] * vec2[0]
            
            # 更新正負號
            if cross != 0:
                if sign == 0:
                    sign = cross  # 記錄第一個非零叉積的正負號
                elif (cross > 0 and sign < 0) or (cross < 0 and sign > 0):
                    return False  # 正負號不一致，則非凸
        
        return True  # 所有叉積正負號一致或全為 0，則為凸
    

    def _get_listOfVertices(self):
        return self.vertices

# Function to check if the polygon is convex polygon or not
def isConvex(Polygon: 'Polygon'):
    return Polygon.isConvex()

# Driver script
if __name__ == '__main__':
    #sample input polygon
    vertices = [ [ 0, 0 ], [ 0, 1 ], 
                 [ 1, 1 ], [ 1, 0 ] ]
    p=Polygon(vertices)
    result="is" if (isConvex(p)) else "is not"
    print(f"The given polygon {p._get_listOfVertices()} {result} convex.")